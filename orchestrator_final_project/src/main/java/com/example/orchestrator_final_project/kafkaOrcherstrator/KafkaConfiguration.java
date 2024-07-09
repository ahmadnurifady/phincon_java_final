package com.example.orchestrator_final_project.kafkaOrcherstrator;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic makeTopicOrchestrator(){
        return TopicBuilder
                .name("SAGA-FLOW")
                .build();
    }

    @Bean
    public NewTopic makeTopicProduct(){
        return TopicBuilder
                .name("TOPIC-PRODUCT")
                .build();
    }

}
