package com.example.orchestrator_final_project.utils.request;

import com.example.orchestrator_final_project.utils.helper.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderResponse {

    private Order order;

    private OrderItem orderItem;

}
