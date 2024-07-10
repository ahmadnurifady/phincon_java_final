package com.example.payment_final_project_java_phincon.utils.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateBalance {

    @NotNull
    private Integer customerId;

    @NotNull
    private float amount;

}
