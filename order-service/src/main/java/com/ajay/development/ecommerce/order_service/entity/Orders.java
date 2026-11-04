package com.ajay.development.ecommerce.order_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double price;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems;
}
