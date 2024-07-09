package com.example.orchestrator_final_project.kafkaOrcherstrator.producer;


import com.example.orchestrator_final_project.utils.request.RequestCreateOrder;
import com.example.orchestrator_final_project.utils.response.DtoMessageKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrchestratorProducer {


    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageSagaFlow(DtoMessageKafka sagaFlow){

        kafkaTemplate.send("SAGA-FLOW", sagaFlow);
        log.info("SENDING MESSAGE TO TOPIC: SAGA-FLOW =={}", sagaFlow);

    }

//    public  void sendMessageOrderEvent(DtoMessageKafka messageOrder){
//
//
//        log.info("SENDING MESSAGE TO TOPIC: topicOrder =={}", messageOrder);
//
//        kafkaTemplate.send("topicOrder", messageOrder);
//    }

//    public void sendMessageProductValidation(DtoMessageKafka messageProduct){
//
//        log.info("SENDING MESSAGE TO TOPIC: TOPIC-PRODUCT =={}", messageProduct);
//
//        kafkaTemplate.send("TOPIC-PRODUCT", messageProduct);
//    }


}
