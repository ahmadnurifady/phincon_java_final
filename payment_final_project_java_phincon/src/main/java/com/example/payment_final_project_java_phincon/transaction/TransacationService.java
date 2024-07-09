package com.example.payment_final_project_java_phincon.transaction;

import com.example.payment_final_project_java_phincon.exeption.DataNotFoundException;
import com.example.payment_final_project_java_phincon.utils.request.RequestCreateTransaction;
import com.example.payment_final_project_java_phincon.utils.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransacationService {

    private  final TransactionRepository repository;

    public Mono<BaseResponse<Transaction>> save(RequestCreateTransaction request) {

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .orderId(request.getOrderId())
                .paymentDate(LocalDateTime.now())
                .mode("METHOD BANK")
                .status("SUCCESS")
                .referenceNumber("reference number")
                .build();

        return repository.save(transaction)
                .then(Mono.just(new BaseResponse<>(
                        HttpStatus.CREATED,
                        "Transaction is Created",
                        transaction
                )));
    }

    public Flux<BaseResponse<Transaction>> getAllTransactions() {
        return repository.findAll()
                .flatMap(transaction -> {
                    return Flux.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Find All Transactions Success",
                            transaction
                    ));
                });
    }

    public Mono<BaseResponse<Transaction>> getTransactionById(long id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Transactions not found. ID: %s", id))))
                .flatMap(transaction ->{
                    return Mono.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Success Find Transaction By Id",
                            transaction
                    ));
                });

    }

    public Mono<BaseResponse<?>> deleteTransaction(long id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Transaction not found. ID: %s", id))))
                .flatMap(value -> repository.deleteById(id)
                        .then(Mono.just(new BaseResponse<>(
                                HttpStatus.OK,
                                "Transaction Success Delete",
                                null
                        ))));
    }


}
