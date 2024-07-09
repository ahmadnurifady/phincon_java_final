package com.example.order_final_project_java_phincon.utils.response;

import com.example.order_final_project_java_phincon.orderItem.OrderItem;
import com.example.order_final_project_java_phincon.orders.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderResponse {

    private Order order;

    private OrderItem orderItem;
}
