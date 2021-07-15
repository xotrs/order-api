package com.cherrypick.order.dto;

import com.cherrypick.order.entity.Gender;
import com.cherrypick.order.entity.Member;
import com.cherrypick.order.entity.Role;
import com.cherrypick.order.lib.RegexPattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @ApiModelProperty(value = "이메일", required = true, example = "xtrd123@xxx.com")
    @NotEmpty
    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @ApiModelProperty(value = "비밀번호", required = true, example = "stR@ingG1cX1")
    @NotEmpty
    @NotNull
    @Size(min = 10, max = 20)
    @RegexPattern(regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#^!%*?&()])[A-Za-z\\d@$#^!%*?&()]{10,}$")
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}