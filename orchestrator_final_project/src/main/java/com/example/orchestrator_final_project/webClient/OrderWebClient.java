package com.example.orchestrator_final_project.webClient;


import com.example.orchestrator_final_project.utils.helper.model.Product;
import com.example.orchestrator_final_project.utils.request.CreateOrderResponse;
import com.example.orchestrator_final_project.utils.request.RequestForValidation;
import com.example.orchestrator_final_project.utils.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderWebClient {


    private final WebClient webClient;

    public OrderWebClient(WebClient.Builder builder){
        webClient = builder.baseUrl("http://localhost:8082/api/order").build();
    }


    public Mono<BaseResponse<CreateOrderResponse>> updateOrder(CreateOrderResponse request){
        return webClient.put()
                .uri("/update")
                .body(Mono.just(request), CreateOrderResponse.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<CreateOrderResponse>>() {})
                .doOnNext(validateResponse -> log.info("UPDATE ORDER == {}", validateResponse))
                .doOnError(throwable -> log.error("UPDATE ORDER failed :: {}", throwable.getMessage()));
    }


    

}
