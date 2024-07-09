package com.example.orchestrator_final_project.utils.helper.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Balance {

    private Integer id;

    private Integer customerId;

    private float amount;

}
