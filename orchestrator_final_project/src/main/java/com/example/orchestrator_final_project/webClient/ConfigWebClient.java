package com.example.orchestrator_final_project.webClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
