package com.example.payment_final_project_java_phincon.transaction;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends R2dbcRepository<Transaction, Long> {
}
