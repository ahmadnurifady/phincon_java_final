package com.example.payment_final_project_java_phincon.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("balance")
public class Balance {

    @Id
    private Integer id;

    @Column("customer_id")
    private Integer customerId;

    private float amount;
}
