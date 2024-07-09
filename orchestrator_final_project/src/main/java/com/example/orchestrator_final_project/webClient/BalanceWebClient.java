package com.example.orchestrator_final_project.webClient;

import com.example.orchestrator_final_project.utils.helper.model.Balance;
import com.example.orchestrator_final_project.utils.helper.model.Product;
import com.example.orchestrator_final_project.utils.request.RequestForValidation;
import com.example.orchestrator_final_project.utils.request.RequestValidateCustomerBalance;
import com.example.orchestrator_final_project.utils.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BalanceWebClient {


    private WebClient webClient;

    public BalanceWebClient(WebClient.Builder builder){
        webClient = builder.baseUrl("http://localhost:8084/api/balance").build();
    }


    public Mono<BaseResponse<Balance>> payment(RequestValidateCustomerBalance request){
        return webClient.put()
                .uri("/payment")
                .body(Mono.just(request), RequestValidateCustomerBalance.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<Balance>>() {})
                .doOnNext(validateResponse -> log.info("BALANCE PAYMENT == {}", validateResponse))
                .doOnError(throwable -> log.error("Balance reservation failed :: {}", throwable.getMessage()));
    }

}
