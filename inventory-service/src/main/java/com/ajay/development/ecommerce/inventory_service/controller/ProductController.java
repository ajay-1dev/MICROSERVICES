package com.ajay.development.ecommerce.inventory_service.controller;


import com.ajay.development.ecommerce.inventory_service.dto.OrderRequestDto;
import com.ajay.development.ecommerce.inventory_service.dto.ProductDto;
import com.ajay.development.ecommerce.inventory_service.service.ProductService;
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


    @GetMapping("/fetchOrders")
    public ResponseEntity<String> fetchFromOrdersService(){
        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service")
                .getFirst();

//        String responce = restClient.get()
//                .uri(serviceInstance.getUri()+"/api/v1/orders/hello")
//                .retrieve()
//                .body(String.class);
//        return ResponseEntity.ok(responce);

        //recommended practice
        return restClient.get()
                .uri(serviceInstance.getUri() + "/api/v1/orders/hello")
                .retrieve()
                .toEntity(String.class);

    }

    @GetMapping("/fetchOrders/{id}")
    public ResponseEntity<OrderRequestDto> fetchFromOrdersServiceWithId(@PathVariable Long id){
        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service")
                .getFirst();

//        String responce = restClient.get()
//                .uri(serviceInstance.getUri()+"/api/v1/orders/hello")
//                .retrieve()
//                .body(String.class);
//        return ResponseEntity.ok(responce);

        //recommended practice
        return restClient.get()
                .uri(serviceInstance.getUri() + "/api/v1/orders/"+id)
                .retrieve()
                .toEntity(OrderRequestDto.class);

    }




    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok(productService.getAllInventory());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
