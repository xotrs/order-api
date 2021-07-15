package com.cherrypick.order.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ResponseError {
    @ApiModelProperty(value = "메시지", required = true)
    private String message;
    @ApiModelProperty(value = "상태값", required = true)
    private int status;

    ResponseError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }
}