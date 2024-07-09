package com.example.payment_final_project_java_phincon.balance;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BalanceRepository extends R2dbcRepository<Balance, Integer> {

    Mono<Balance> findBalanceByCustomerId(Integer customerId);
}
