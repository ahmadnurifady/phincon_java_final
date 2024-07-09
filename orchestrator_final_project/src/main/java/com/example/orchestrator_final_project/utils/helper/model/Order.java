package com.example.orchestrator_final_project.utils.helper.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private Integer id;

    private Integer customerId;

    private LocalDateTime orderDate;

    private String billingAddress;

    private String orderStatus;

    private String paymentMethod;

    private String shippingAddress;

    private float totalAmount;

}
