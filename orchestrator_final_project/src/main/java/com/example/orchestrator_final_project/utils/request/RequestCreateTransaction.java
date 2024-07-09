package com.example.orchestrator_final_project.utils.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateTransaction {

    private float amount;

    private float orderId;

}
