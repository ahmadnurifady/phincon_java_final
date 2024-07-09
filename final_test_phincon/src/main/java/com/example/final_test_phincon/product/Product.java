package com.example.final_test_phincon.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("product")
public class Product {

    @Id
    private int id;

    private String name;

    private float price;

    private String category;

    private String description;

    @Column(value = "image_url")
    private String imageUrl;

    @Column(value = "stock_quantity")
    private int stockQuantity;

    @Column(value = "created_at")
    private Instant createdAt;

    @Column(value = "updated_at")
    private Instant updatedAt;
}
