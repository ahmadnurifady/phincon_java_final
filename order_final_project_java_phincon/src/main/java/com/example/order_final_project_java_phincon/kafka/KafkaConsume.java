package com.example.order_final_project_java_phincon.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsume {
//    @KafkaListener(topics = "topicOrder", groupId = "groupOrder")
    public void consumeMsg(String msg){
        log.info("CONSUMING MESSAGE FROM TOPIC: MYTOPIC == {}", msg);
    }

}
