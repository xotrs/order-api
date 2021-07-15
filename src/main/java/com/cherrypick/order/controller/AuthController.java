package com.cherrypick.order.controller;

import com.cherrypick.order.dto.*;
import com.cherrypick.order.dto.response.Response;
import com.cherrypick.order.exception.BadRequestException;
import com.cherrypick.order.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Auth")
@Slf4j
@RestController
@RequestMapping("apis/v1/auths")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @ApiOperation(value = "회원 가입")
    @PostMapping("/signup")
    public Response<MemberResponseDto> signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        if (authService.hasAccount(memberRequestDto.getEmail())) {
            throw new BadRequestException("이미 가입한 회원입니다.");
        }

        return Response.ok(authService.signup(memberRequestDto));
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public Response<JwtDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return Response.ok(authService.login(loginRequestDto));
    }

    @ApiOperation(value = "로그아웃", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/logout")
    public Response<Integer> logout() {
        authService.logout();
        return Response.ok();
    }

    @ApiOperation(value = "재인증")
    @PostMapping("/re-auth")
    public Response<JwtDto> reAuth(@RequestBody TokenRequestDto tokenRequestDto) {
        return Response.ok(authService.reAuth(tokenRequestDto));
    }
}
