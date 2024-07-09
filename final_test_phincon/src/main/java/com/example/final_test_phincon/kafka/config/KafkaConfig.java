package com.example.final_test_phincon.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaConfig {

    public NewTopic createTopic(){
        return TopicBuilder
                .name("validationProductIsNotNull")
                .build();
    }
}
