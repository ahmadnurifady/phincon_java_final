package com.example.order_final_project_java_phincon.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigTopic {

    @Bean
    public NewTopic makeTopic(){
        return TopicBuilder
                .name("TOPIC-ORDER")
                .build();
    }

}
