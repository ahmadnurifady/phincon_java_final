package com.example.order_final_project_java_phincon.utils.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateOrderItem {

    private Integer productId;

    private Integer quantity;

    private Integer orderId;

}
