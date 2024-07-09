package com.example.final_test_phincon.utils.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestForValidation {

    private Integer productId;

    private Integer quantity;

}
