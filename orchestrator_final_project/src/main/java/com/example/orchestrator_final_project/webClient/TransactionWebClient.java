package com.example.orchestrator_final_project.webClient;

import com.example.orchestrator_final_project.utils.helper.model.Product;
import com.example.orchestrator_final_project.utils.helper.model.Transaction;
import com.example.orchestrator_final_project.utils.request.RequestCreateTransaction;
import com.example.orchestrator_final_project.utils.request.RequestForValidation;
import com.example.orchestrator_final_project.utils.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TransactionWebClient {

    private WebClient webClient;

    public TransactionWebClient(WebClient.Builder builder){
        webClient = builder.baseUrl("http://localhost:8084/api/transaction").build();
    }


    public Mono<BaseResponse<Transaction>> createTransaction(RequestCreateTransaction request){
        return webClient.post()
                .uri("/create")
                .body(Mono.just(request), RequestCreateTransaction.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<Transaction>>() {})
                .doOnNext(validateResponse -> log.info("TRANSACTION CREATE == {}", validateResponse))
                .doOnError(throwable -> log.error("Product reservation failed :: {}", throwable.getMessage()));
    }

}
