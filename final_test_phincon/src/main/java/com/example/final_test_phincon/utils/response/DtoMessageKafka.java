package com.example.final_test_phincon.utils.response;

import com.example.final_test_phincon.utils.helper.CreateOrderResponse;
import com.example.final_test_phincon.utils.request.RequestCreateOrder;
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
