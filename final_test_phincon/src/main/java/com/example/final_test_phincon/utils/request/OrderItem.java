package com.example.final_test_phincon.utils.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    private Integer id;

    private float price;

    private Integer productId;

    private Integer quantity;

    private Integer orderId;

}
