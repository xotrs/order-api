package com.cherrypick.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {
    @ApiModelProperty(value = "인증 타입(Bearer)", required = true)
    private String grantType;
    @ApiModelProperty(value = "access token", required = true)
    private String accessToken;
    @ApiModelProperty(value = "refresh token", required = true)
    private String refreshToken;
    @ApiModelProperty(value = "access token 만료 시간", required = true)
    private Long accessTokenExpiresIn;
}