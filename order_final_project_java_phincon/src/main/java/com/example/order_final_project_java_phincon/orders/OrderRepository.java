package com.example.order_final_project_java_phincon.orders;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {
}
