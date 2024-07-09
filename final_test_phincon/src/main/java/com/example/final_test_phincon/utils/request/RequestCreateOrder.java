package com.example.final_test_phincon.utils.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateOrder {

    private Integer customerId;

    private LocalDateTime orderDate;

    private String billingAddress;

    private String orderStatus;

    private String paymentMethod;

    private String shippingAddress;

    private float totalAmount;

    private OrderItem orderItem;
}
