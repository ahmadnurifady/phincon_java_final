package com.example.final_test_phincon.utils.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class RequestCreateProduct {

    @NotBlank
    @Size(min = 15, max = 255)
    private String name;

    @NotNull
    @Size(min = 1)
    private long price;

    @NotBlank
    @Size(min = 15, max = 255)
    private String category;

    @NotBlank
    @Size(min = 15, max = 255)
    private String description;

    @NotBlank
    @Size(min = 15, max = 255)
    private String imageUrl;

    @NotNull
    @Size(min = 1)
    private int stockQuantity;
}
