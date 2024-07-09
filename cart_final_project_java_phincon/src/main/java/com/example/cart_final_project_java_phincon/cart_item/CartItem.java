package com.example.cart_final_project_java_phincon.cart_item;


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
@Table(value = "cart_item")
public class CartItem {

    @Id
    private Integer id;

    @Column(value = "product_id")
    private Integer productId;

    private Integer quantity;

    @Column(value = "cart_id")
    private Integer cartId;

}
