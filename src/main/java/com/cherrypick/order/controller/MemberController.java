package com.cherrypick.order.controller;

import com.cherrypick.order.dto.MemberOrderResponseDto;
import com.cherrypick.order.dto.MemberResponseDto;
import com.cherrypick.order.dto.OrderResponseDto;
import com.cherrypick.order.dto.response.Response;
import com.cherrypick.order.entity.Member;
import com.cherrypick.order.entity.SearchType;
import com.cherrypick.order.exception.NotFoundException;
import com.cherrypick.order.service.MemberService;
import com.cherrypick.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Member")
@Slf4j
@RestController
@RequestMapping("apis/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final OrderService orderService;

    public MemberController(MemberService memberService, OrderService orderService) {
        this.memberService = memberService;
        this.orderService = orderService;
    }

    @ApiOperation(value = "여러 회원 목록 조회", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping
    public Response<List<MemberOrderResponseDto>> getMembers(@ApiParam(value = "검색 키워드") @RequestParam("keyword") String keyword,
                                                             @ApiParam(value = "검색 타입(NAME, EMAIL)") @RequestParam("type") SearchType type,
                                                             @ApiParam(value = "페이지", example = "1") @RequestParam("page") int page) {
        List<Member> members = memberService.getMembersBySearchKeywordAndTypeWithPagination(keyword, type, page);
        return Response.ok(memberService.generateMembersWithLatestOrder(members));
    }


    @ApiOperation(value = "단일 회원 상세 정보 조회", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/{memberId}")
    public Response<MemberResponseDto> getMember(@ApiParam(value = "회원 ID") @PathVariable Long memberId) {
        MemberResponseDto memberResponseDto = memberService.getMemberById(memberId);
        if (memberResponseDto == null) throw new NotFoundException("존재하지 않는 회원입니다.");
        return Response.ok(memberResponseDto);
    }

    @ApiOperation(value = "단일 회원의 주문 목록 조회", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/{memberId}/orders")
    public Response<List<OrderResponseDto>> getOrders(@ApiParam(value = "회원 ID") @PathVariable Long memberId) {
        return Response.ok(orderService.getOrdersByMemberId(memberId));
    }
}