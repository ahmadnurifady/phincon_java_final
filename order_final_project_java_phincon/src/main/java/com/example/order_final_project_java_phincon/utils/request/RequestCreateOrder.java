package com.example.order_final_project_java_phincon.utils.request;

import com.example.order_final_project_java_phincon.orderItem.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Min(1)
    private Integer customerId;

    @NotNull
    @Size(max = 255, min = 25)
    private String billingAddress;

    @NotNull
    @Size(max = 50, min = 25)
    private String paymentMethod;

    @NotNull
    @Size(max = 255, min = 25)
    private String shippingAddress;

    @NotNull
    private RequestCreateOrderItem orderItem;
}
