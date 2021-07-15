package com.cherrypick.order.dto;

import com.cherrypick.order.entity.Gender;
import com.cherrypick.order.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    @ApiModelProperty(value = "이메일", required = true)
    private String email;
    @ApiModelProperty(value = "이름", required = true)
    private String name;
    @ApiModelProperty(value = "닉네임", required = true)
    private String nickName;
    @ApiModelProperty(value = "전화번호", required = true)
    private String phone;
    @ApiModelProperty(value = "성별")
    private Gender gender;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail(), member.getName(), member.getNickName(), member.getPhone(), member.getGender());
    }
}