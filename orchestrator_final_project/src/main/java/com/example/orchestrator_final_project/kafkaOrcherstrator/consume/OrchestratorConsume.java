package com.example.orchestrator_final_project.kafkaOrcherstrator.consume;

import com.example.orchestrator_final_project.utils.helper.ObjectMapperConvert;
import com.example.orchestrator_final_project.utils.response.DtoMessageKafka;
import com.example.orchestrator_final_project.utils.response.KafkaResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrchestratorConsume {

    @Autowired
    ObjectMapperConvert objectMapperConvert;

    @KafkaListener(topics = "TOPIC-PRODUCT")
    public void consumeMessageTopicProduct(String msg){

        DtoMessageKafka message = objectMapperConvert.convertFromJson(msg, DtoMessageKafka.class);

        log.info("CONSUMING MESSAGE FROM TOPIC: SAGA-FLOW == {}", message);

    }


}
