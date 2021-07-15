package com.cherrypick.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequestDto {
    @ApiModelProperty(value = "access token", required = true)
    private String accessToken;
    @ApiModelProperty(value = "refresh token", required = true)
    private String refreshToken;
}
