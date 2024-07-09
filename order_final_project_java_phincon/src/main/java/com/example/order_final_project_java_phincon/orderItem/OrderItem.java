package com.example.order_final_project_java_phincon.orderItem;

import com.example.order_final_project_java_phincon.orders.Order;
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
@Table("order_item")
public class OrderItem {
    @Id
    private Integer id;

    private float price;

    @Column("product_id")
    private Integer productId;

    private Integer quantity;

    @Column("order_id")
    private Integer orderId;
}
