package com.example.payment_final_project_java_phincon.balance;

import com.example.payment_final_project_java_phincon.exeption.DataNotFoundException;
import com.example.payment_final_project_java_phincon.utils.request.RequestCreateBalance;
import com.example.payment_final_project_java_phincon.utils.request.RequestValidateCustomerBalance;
import com.example.payment_final_project_java_phincon.utils.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository repository;

    public Mono<BaseResponse<Balance>> save(RequestCreateBalance request) {

        Balance balance = Balance.builder()
                .customerId(request.getCustomerId())
                .amount(request.getAmount())
                .build();

        return repository.save(balance)
                .doOnSuccess(balanceCreate -> log.info("CREATE BALANCE SUCCESS == {}", balanceCreate))
                .then(Mono.just(new BaseResponse<>(
                    HttpStatus.CREATED,
                    "Create Balance Success",
                    balance
                )));
    }

    public Flux<BaseResponse<Balance>> getAllBalances() {
        return repository.findAll()
                .flatMap(order -> Flux.just(new BaseResponse<>(
                        HttpStatus.OK,
                        "Find All Balances Success",
                        order
                )));
    }

    public Mono<BaseResponse<Balance>> getBalanceById(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Balance not found. ID: %s", id))))
                .flatMap(order -> Mono.just(new BaseResponse<>(
                        HttpStatus.OK,
                        "Success Find Balance By Id",
                        order
                )));

    }

    public Mono<BaseResponse<?>> deleteBalance(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Balance not found. ID: %s", id))))
                .flatMap(value -> repository.deleteById(id)
                        .then(Mono.just(new BaseResponse<>(
                                HttpStatus.OK,
                                "Balance Success Delete",
                                null
                        ))));
    }

    public Mono<BaseResponse<Balance>> credit(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Balance not found. ID: %s", id))))
                .flatMap(order -> Mono.just(new BaseResponse<>(
                        HttpStatus.OK,
                        "Success Find Balance By Id",
                        order
                )));

    }

    public Mono<BaseResponse<Balance>> customerBalanceProses(RequestValidateCustomerBalance request) {
        return repository.findBalanceByCustomerId(request.getCustomerId())
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Balance not found. ID: %s", request.getCustomerId()))))
                .flatMap(balance -> {
                    log.info("DATA BALANCE == {}", balance);

                    if (balance.getAmount() >= request.getAmount()) {
                        balance.setAmount(balance.getAmount() - request.getAmount());
                        return repository.save(balance)
                                .doOnSuccess(savedBalance -> log.info("BALANCE IS UPDATED"))
                                .map(savedBalance -> new BaseResponse<>(
                                        HttpStatus.OK,
                                        "Balance updated successfully",
                                        savedBalance
                                ));
                    } else {
                        log.info("INSUFFICIENT BALANCE, ROLLBACK");
                        return Mono.error(new DataNotFoundException(String.format("Balance not found. ID: %s", request.getCustomerId())));
                    }
                });
    }


}
