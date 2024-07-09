package com.example.orchestrator_final_project.utils.response;

import com.example.orchestrator_final_project.utils.request.CreateOrderResponse;
import com.example.orchestrator_final_project.utils.request.RequestCreateOrder;
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
