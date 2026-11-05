package com.ajay.development.ecommerce.order_service.dto;

import com.ajay.development.ecommerce.order_service.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private List<OrderRequestItemDto> orderItems;
    private Double totalPrice;

}
