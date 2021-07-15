package com.cherrypick.order.service;

import com.cherrypick.order.dto.LatestOrderResponseDto;
import com.cherrypick.order.dto.MemberOrderResponseDto;
import com.cherrypick.order.dto.MemberResponseDto;
import com.cherrypick.order.entity.Member;
import com.cherrypick.order.entity.SearchType;
import com.cherrypick.order.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    private final int LIMIT_SIZE = 10;
    private final OrderService orderService;
    private final MemberRepository memberRepository;

    MemberService(OrderService orderService, MemberRepository memberRepository) {
        this.orderService = orderService;
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) return null;
        return MemberResponseDto.of(member.get());
    }

    @Transactional(readOnly = true)
    public List<Member> getMembersBySearchKeywordAndTypeWithPagination(String keyword, SearchType type, int page) {
        List<Member> members = new ArrayList<Member>();
        if (type.equals(SearchType.EMAIL)) {
            members = memberRepository.findAllByEmailContains(keyword, PageRequest.of(page - 1, LIMIT_SIZE));
        } else if(type.equals(SearchType.NAME)) {
            members = memberRepository.findAllByNameContains(keyword, PageRequest.of(page - 1, LIMIT_SIZE));
        }
        return members;
    }

    @Transactional(readOnly = true)
    public List<MemberOrderResponseDto> generateMembersWithLatestOrder(List<Member> members) {
        List<MemberOrderResponseDto> memberOrderResponse = new ArrayList<>();
        for (Member member: members) {
            LatestOrderResponseDto orderResponseDto = orderService.getLatestOrderByMember(member);
            memberOrderResponse.add(new MemberOrderResponseDto().of(member, orderResponseDto));
        }

        return memberOrderResponse;
    }
}