package com.ajay.development.ecommerce.inventory_service.controller;


import com.ajay.development.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.ajay.development.ecommerce.inventory_service.dto.OrderRequestDto;
import com.ajay.development.ecommerce.inventory_service.dto.ProductDto;
import com.ajay.development.ecommerce.inventory_service.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrdersFeignClient ordersFeignClient;


    @GetMapping("/fetchOrders")
    public ResponseEntity<String> fetchFromOrdersService(HttpServletRequest request) {
//        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service")
//                .getFirst();
//
//        String responce = restClient.get()
//                .uri(serviceInstance.getUri()+"/api/v1/orders/hello")
//                .retrieve()
//                .body(String.class);
//        return ResponseEntity.ok(responce);
//
//        //recommended practice
//        return restClient.get()
//                .uri(serviceInstance.getUri() + "/orders/core/hello")
//                .retrieve()
//                .toEntity(String.class);
        String responce = ordersFeignClient.fetchOrders();
        return ResponseEntity.ok(responce);

    }

    @GetMapping("/fetchOrders/{id}")
    public ResponseEntity<OrderRequestDto> fetchFromOrdersServiceWithId(@PathVariable Long id){
//        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service")
//                .getFirst();

//        String responce = restClient.get()
//                .uri(serviceInstance.getUri()+"/api/v1/orders/hello")
//                .retrieve()
//                .body(String.class);
//        return ResponseEntity.ok(responce);

        //recommended practice
//        return restClient.get()
//                .uri(serviceInstance.getUri() + "/orders/core/"+id)
//                .retrieve()
//                .toEntity(OrderRequestDto.class);
        return ResponseEntity.ok(ordersFeignClient.fetchOrderById(id));

    }




    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok(productService.getAllInventory());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }


    @PostMapping("reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequestDto){

        Double price = productService.reduceStocks(orderRequestDto);
        return ResponseEntity.ok(price);

    }

}
