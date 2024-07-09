package com.example.orchestrator_final_project.webClient;


import com.example.orchestrator_final_project.utils.helper.model.Product;
import com.example.orchestrator_final_project.utils.request.RequestForValidation;
import com.example.orchestrator_final_project.utils.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductWebClient {

    private WebClient webClient;

    public ProductWebClient(WebClient.Builder builder){
        webClient = builder.baseUrl("http://localhost:8080/api/products").build();
    }


    public Mono<BaseResponse<Product>> validationProduct(RequestForValidation request){
        return webClient.put()
                .uri("/validationProduct")
                .body(Mono.just(request), RequestForValidation.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<BaseResponse<Product>>() {})
                .doOnNext(validateResponse -> log.info("PRODUCT VALIDATION == {}", validateResponse))
                .doOnError(throwable -> log.error("Product reservation failed :: {}", throwable.getMessage()));
    }

}
