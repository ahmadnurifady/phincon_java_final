package com.example.final_test_phincon.kafka;

import com.example.final_test_phincon.product.ProductService;
import com.example.final_test_phincon.utils.helper.ObjectMapperConvert;
import com.example.final_test_phincon.utils.response.DtoMessageKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final ObjectMapperConvert objectMapperConvert;
    private final ProductService productService;

//    @KafkaListener(topics = "TOPIC-PRODUCT", groupId = "groupProduct")
//    public void consumeMessageToValidationProductInOrder(String msg){
//
//        DtoMessageKafka kafkaResponseMessage = objectMapperConvert.convertFromJson(msg, DtoMessageKafka.class);
//        log.info("CONSUMING MESSAGE FROM TOPIC: TOPIC-PRODUCT == {}", kafkaResponseMessage);
//
//        productService.validationProductFromMessageOrder(kafkaResponseMessage).subscribe();
//
//    }


}
