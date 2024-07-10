package com.example.payment_final_project_java_phincon.balance;

import com.example.payment_final_project_java_phincon.utils.request.RequestCreateBalance;
import com.example.payment_final_project_java_phincon.utils.request.RequestValidateCustomerBalance;
import com.example.payment_final_project_java_phincon.utils.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService service;

    @PostMapping("/create")
    public Mono<BaseResponse<Balance>> createBalance(@RequestBody RequestCreateBalance request) {
        Mono<BaseResponse<Balance>> result = service.save(request);

        return result;
    }

    @GetMapping("/all")
    public Flux<BaseResponse<Balance>> getAllBalances() {

        Flux<BaseResponse<Balance>> result = service.getAllBalances();
        return result;
    }

    @GetMapping("/{id}")
    public Mono<BaseResponse<Balance>> getBalanceById(@PathVariable int id) {

        Mono<BaseResponse<Balance>> result = service.getBalanceById(id);

        return result;
    }


    @DeleteMapping("/delete/{id}")
    public Mono<BaseResponse<?>> deleteBalance(@PathVariable int id) {

        Mono<BaseResponse<?>> result = service.deleteBalance(id);

        return result;
    }

    @PutMapping("/payment")
    public Mono<BaseResponse<Balance>> paymentCustomerBalance(@RequestBody RequestValidateCustomerBalance request){

        return service.customerBalanceProses(request);


    }

}
