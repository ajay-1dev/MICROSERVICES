package com.ajay.development.ecommerce.order_service.service;


import com.ajay.development.ecommerce.order_service.client.InventoryFeignClient;
import com.ajay.development.ecommerce.order_service.dto.OrderRequestDto;
import com.ajay.development.ecommerce.order_service.entity.OrderItem;
import com.ajay.development.ecommerce.order_service.entity.OrderStatus;
import com.ajay.development.ecommerce.order_service.entity.Orders;
import com.ajay.development.ecommerce.order_service.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository orderRepository;
    private final ModelMapper modelMapper;
    private final InventoryFeignClient inventoryFeignClient;

    public List<OrderRequestDto> getAllOrders() {
        log.info("Fetching all orders");
        List<Orders> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderRequestDto.class)).toList();
    }

    public OrderRequestDto getOrderById(Long id) {
        log.info("Fetching order with ID: {}", id);
        Orders order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderRequestDto.class);
    }

    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        Double totalPrice = inventoryFeignClient.reduceStocks(orderRequestDto);
        Orders order = modelMapper.map(orderRequestDto, Orders.class);
        for(OrderItem orderItem:order.getOrderItems()){
            orderItem.setOrder(order);
        }
        order.setPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CONFIRM);
        Orders savedOrder = orderRepository.save(order);
        OrderRequestDto response =  modelMapper.map(savedOrder, OrderRequestDto.class);
        return response;

    }
}
