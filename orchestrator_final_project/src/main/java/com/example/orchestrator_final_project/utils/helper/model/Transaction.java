package com.example.orchestrator_final_project.utils.helper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    private long id;

    private float amount;

    private float orderId;

    private LocalDateTime paymentDate;

    private String mode;

    private String status;

    private String referenceNumber;

}
