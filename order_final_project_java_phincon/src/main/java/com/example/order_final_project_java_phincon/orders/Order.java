package com.example.order_final_project_java_phincon.orders;

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
@Table("orders")
public class Order {

    @Id
    private Integer id;

    @Column("customer_id")
    private Integer customerId;

    @Column("order_date")
    private LocalDateTime orderDate;

    @Column("billing_address")
    private String billingAddress;

    @Column("order_status")
    private String orderStatus;

    @Column("payment_method")
    private String paymentMethod;

    @Column("shipping_address")
    private String shippingAddress;

    @Column("total_amount")
    private float totalAmount;
}
