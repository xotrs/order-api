package com.cherrypick.order.dto;

import com.cherrypick.order.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LatestOrderResponseDto {
    @ApiModelProperty(value = "주문 ID", required = true)
    private String orderId;
    @ApiModelProperty(value = "상품명", required = true)
    private String productTitle;
    @ApiModelProperty(value = "주문일시", required = true)
    private LocalDateTime createdAt;

    public static LatestOrderResponseDto of(Order order) {
        return new LatestOrderResponseDto(order.getId(), order.getProductTitle(), order.getCreatedAt());
    }
}
