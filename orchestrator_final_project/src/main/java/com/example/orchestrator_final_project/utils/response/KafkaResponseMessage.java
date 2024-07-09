package com.example.orchestrator_final_project.utils.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaResponseMessage{

    private Integer productId;

    private Integer quantity;

    private Integer orderId;

    private Integer customerId;

    private String orderStatus;

    private Integer orderItemId;

    private float totalAmount;

}
