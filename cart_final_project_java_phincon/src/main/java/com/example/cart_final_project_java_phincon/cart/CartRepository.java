package com.example.cart_final_project_java_phincon.cart;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends R2dbcRepository<Cart, Integer> {
}
