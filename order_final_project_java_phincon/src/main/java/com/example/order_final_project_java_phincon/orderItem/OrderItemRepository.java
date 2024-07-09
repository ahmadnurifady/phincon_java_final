package com.example.order_final_project_java_phincon.orderItem;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends ReactiveCrudRepository<OrderItem, Integer> {

}
