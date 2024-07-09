package com.example.payment_final_project_java_phincon.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("transaction")
public class Transaction {

    @Id
    private long id;

    private float amount;

    @Column(value = "order_id")
    private float orderId;

    @Column(value = "payment_date")
    private LocalDateTime paymentDate;

    private String mode;

    private String status;

    @Column(value = "reference_number")
    private String referenceNumber;

}
