package com.ajay.development.ecommerce.inventory_service.controller;


import com.ajay.development.ecommerce.inventory_service.dto.ProductDto;
import com.ajay.development.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok(productService.getAllInventory());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
