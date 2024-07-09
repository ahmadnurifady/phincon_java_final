package com.example.cart_final_project_java_phincon.utils.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCreateCart {

    private String status;

    private float totalPrice;

}
