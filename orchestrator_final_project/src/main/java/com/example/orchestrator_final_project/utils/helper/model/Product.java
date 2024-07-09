package com.example.orchestrator_final_project.utils.helper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private int id;

    private String name;

    private float price;

    private String category;

    private String description;

    private String imageUrl;

    private int stockQuantity;

    private Instant createdAt;

    private Instant updatedAt;
}
