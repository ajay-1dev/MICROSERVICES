package com.ajay.development.ecommerce.order_service.controller;

import com.ajay.development.ecommerce.order_service.client.InventoryFeignClient;
import com.ajay.development.ecommerce.order_service.dto.OrderRequestDto;
import com.ajay.development.ecommerce.order_service.entity.Orders;
import com.ajay.development.ecommerce.order_service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/core")
public class OrderController {


    private final OrdersService ordersService;
    private final InventoryFeignClient  inventoryFeignClient;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello from order-service");
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrdersById(@PathVariable Long id) {
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }


    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {

        return ResponseEntity.ok(ordersService.createOrder(orderRequestDto));
    }
}
