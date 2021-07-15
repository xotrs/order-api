package com.cherrypick.order.dto;

import com.cherrypick.order.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberOrderResponseDto {
    @ApiModelProperty(value = "회원 ID", required = true)
    private Long id;
    @ApiModelProperty(value = "이메일", required = true)
    private String email;
    @ApiModelProperty(value = "이름", required = true)
    private String name;
    @ApiModelProperty(value = "닉네임", required = true)
    private String nickName;
    @ApiModelProperty(value = "전화번호", required = true)
    private String phone;
    @ApiModelProperty(value = "최근 주문 정보 1개")
    private LatestOrderResponseDto latestOrder;

    public static MemberOrderResponseDto of(Member member, LatestOrderResponseDto latestOrderResponseDto) {
        return new MemberOrderResponseDto(member.getId(), member.getEmail(), member.getName(), member.getNickName(), member.getPhone(), latestOrderResponseDto);
    }
}