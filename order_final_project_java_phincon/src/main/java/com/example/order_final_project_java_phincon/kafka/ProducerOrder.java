package com.example.order_final_project_java_phincon.kafka;

import com.example.order_final_project_java_phincon.orderItem.OrderItem;
import com.example.order_final_project_java_phincon.utils.helper.DtoMessageKafka;
import com.example.order_final_project_java_phincon.utils.response.BaseResponse;
import com.example.order_final_project_java_phincon.utils.response.KafkaResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerOrder {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(KafkaResponseMessage msg) {



        kafkaTemplate.send("TOPIC-ORDER", msg);
        log.info("SENDING MESSAGE TO TOPIC: TOPIC-ORDER =={}", msg);

    }

    public void sendMessageSagaFlow(DtoMessageKafka sagaFlow){

        kafkaTemplate.send("SAGA-FLOW", sagaFlow);

        log.info("SENDING MESSAGE TO TOPIC: SAGA-FLOW =={}", sagaFlow);
    }

//    public void sendMessageToTopicProduct(DtoMessageKafka dataOrder){
//
//        kafkaTemplate.send("TOPIC-PRODUCT", dataOrder);
//
//        log.info("SENDING MESSAGE TO TOPIC: TOPIC-PRODUCT =={}", dataOrder);
//    }


}
