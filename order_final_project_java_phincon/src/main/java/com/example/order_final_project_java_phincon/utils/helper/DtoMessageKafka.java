package com.example.order_final_project_java_phincon.utils.helper;

import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrder;
import com.example.order_final_project_java_phincon.utils.response.CreateOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoMessageKafka {

    private String statusSaga;

    private CreateOrderResponse data;
}
