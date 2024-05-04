package com.example.buyer_project.product;

import com.example.buyer_project.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "product_tb")
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true, length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer qty;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Product(Integer id, String name, Integer price, Integer qty, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.createdAt = createdAt;
    }

}