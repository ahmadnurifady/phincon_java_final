package com.example.final_test_phincon.utils.request;

import jakarta.validation.constraints.Min;
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
public class RequestCreateProduct {

    @NotNull
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @Min(1)
    private long price;

    @NotNull
    @Size(min = 10, max = 255)
    private String category;

    @NotNull
    @Size(min = 15, max = 255)
    private String description;

    @NotNull
    @Size(min = 10, max = 255)
    private String imageUrl;

    @NotNull
    @Min(1)
    private int stockQuantity;
}
