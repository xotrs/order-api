package com.cherrypick.order.dto;

import com.cherrypick.order.entity.Gender;
import com.cherrypick.order.entity.Member;
import com.cherrypick.order.entity.Role;
import com.cherrypick.order.lib.RegexPattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    @ApiModelProperty(value = "이메일", required = true, example = "xtrd123@xxx.com")
    @NotEmpty
    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @ApiModelProperty(value = "이름", required = true, example = "임영록")
    @NotEmpty
    @NotNull
    @Size(max = 20)
    @RegexPattern(regex = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|\\*]+$")
    private String name;

    @ApiModelProperty(value = "닉네임", required = true, example = "cherrypick")
    @NotEmpty
    @NotNull
    @Size(max = 30)
    @RegexPattern(regex = "^[a-z\\*]+$")
    private String nickName;

    @ApiModelProperty(value = "비밀번호", required = true, example = "stR@ingG1cX1")
    @NotEmpty
    @NotNull
    @Size(min = 10, max = 20)
    @RegexPattern(regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#^!%*?&()])[A-Za-z\\d@$#^!%*?&()]{10,}$")
    private String password;

    @ApiModelProperty(value = "전화번호", required = true, example = "01030227642")
    @NotEmpty
    @NotNull
    @Size(max = 20)
    @RegexPattern(regex = "^01([0|1|6|7|8|9])([0-9]{4})([0-9]{4})$")
    private String phone;


    @ApiModelProperty(value = "성별", example = "M", allowableValues = "M, F")
    private Gender gender;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .name(name)
                .nickName(nickName)
                .phone(phone)
                .gender(gender)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();
    }
}