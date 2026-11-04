package com.ajay.development.ecommerce.order_service.controller;

import com.ajay.development.ecommerce.order_service.dto.OrderRequestDto;
import com.ajay.development.ecommerce.order_service.entity.Orders;
import com.ajay.development.ecommerce.order_service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {


    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }
}
