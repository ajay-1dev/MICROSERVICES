package com.ajay.development.ecommerce.order_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Orders order;
}
