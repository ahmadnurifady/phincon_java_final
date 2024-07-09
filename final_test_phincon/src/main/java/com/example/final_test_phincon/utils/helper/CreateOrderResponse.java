package com.example.final_test_phincon.utils.helper;


import com.example.final_test_phincon.utils.request.Order;
import com.example.final_test_phincon.utils.request.OrderItem;
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
