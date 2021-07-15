package com.cherrypick.order.service;

import com.cherrypick.order.dto.*;
import com.cherrypick.order.entity.Member;
import com.cherrypick.order.entity.Token;
import com.cherrypick.order.exception.BadRequestException;
import com.cherrypick.order.jwt.JwtProvider;
import com.cherrypick.order.repository.MemberRepository;
import com.cherrypick.order.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;

    public AuthService(AuthenticationManagerBuilder authenticationManagerBuilder, MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, TokenRepository tokenRepository) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.tokenRepository = tokenRepository;
    }

    public boolean hasAccount(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public JwtDto login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtDto jwtDto = jwtProvider.generateTokenDto(authentication);

        Token token = Token.builder()
                .accessToken(jwtDto.getAccessToken())
                .refreshToken(jwtDto.getRefreshToken())
                .memberId(Long.parseLong(authentication.getName()))
                .build();

        tokenRepository.save(token);

        return jwtDto;
    }

    @Transactional
    public Integer logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) throw new BadRequestException("회원 정보가 없습니다");
        return tokenRepository.deleteByMemberId(Long.parseLong(authentication.getName()));
    }

    @Transactional
    public JwtDto reAuth(TokenRequestDto tokenRequestDto) {
        if (!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new BadRequestException("토큰이 유효하지 않습니다.");
        }

        Authentication authentication = jwtProvider.getAuthentication(tokenRequestDto.getAccessToken());

        Token token = tokenRepository.findByMemberId(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new BadRequestException("로그아웃 된 회원입니다."));

        if (!token.getRefreshToken().equals(tokenRequestDto.getRefreshToken())) {
            throw new BadRequestException("토큰의 회원 정보가 일치하지 않습니다.");
        }

        JwtDto jwtDto = jwtProvider.generateTokenDto(authentication);

        Token newToken = token.updateToken(jwtDto.getAccessToken(), jwtDto.getRefreshToken());
        tokenRepository.save(newToken);

        return jwtDto;
    }
}
