package com.ajay.development.ecommerce.inventory_service.clients;

import com.ajay.development.ecommerce.inventory_service.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "order-service",path = "/orders")
public interface OrdersFeignClient {

    @GetMapping("/core/hello")
    String fetchOrders();

    @GetMapping("/core/{id}")
    OrderRequestDto fetchOrderById(@PathVariable("id") Long id);



}
