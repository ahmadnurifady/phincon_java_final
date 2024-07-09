package com.example.order_final_project_java_phincon.utils.request;

import com.example.order_final_project_java_phincon.orderItem.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
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


    private String billingAddress;


    private String paymentMethod;

    private String shippingAddress;


    private RequestCreateOrderItem orderItem;
}
