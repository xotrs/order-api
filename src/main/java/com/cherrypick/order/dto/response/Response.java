package com.cherrypick.order.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response<T> {

    @ApiModelProperty(value = "성공여부", required = true)
    private final boolean success;
    @ApiModelProperty(value = "데이터", required = true)
    private final T data;
    @ApiModelProperty(value = "에러", required = true)
    private final ResponseError error;

    public static <T> Response<T> ok() {
        return new Response<>(true, null, null);
    }

    public static <T> Response<T> ok(T response) {
        return new Response<>(true, response, null);
    }

    public static Response error(String errorMessage, HttpStatus status) {
        return new Response<>(false, null, new ResponseError(errorMessage, status));
    }
}

