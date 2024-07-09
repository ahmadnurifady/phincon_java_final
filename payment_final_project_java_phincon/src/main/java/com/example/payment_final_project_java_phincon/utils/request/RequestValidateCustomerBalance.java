package com.example.payment_final_project_java_phincon.utils.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestValidateCustomerBalance {

    private Integer customerId;

    private float orderId;

    private float amount;

    

}
