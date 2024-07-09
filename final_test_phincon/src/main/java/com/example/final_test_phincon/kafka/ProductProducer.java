package com.example.final_test_phincon.kafka;


import com.example.final_test_phincon.product.Product;
import com.example.final_test_phincon.utils.response.DtoMessageKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToOrchestrator(DtoMessageKafka messageProduct) {

        log.info("SENDING MESSAGE TO TOPIC: SAGA-FLOW =={}", messageProduct);
        kafkaTemplate.send("SAGA-FLOW", messageProduct);
    }
}
