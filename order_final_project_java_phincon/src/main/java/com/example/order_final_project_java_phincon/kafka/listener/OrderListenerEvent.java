package com.example.order_final_project_java_phincon.kafka.listener;

import com.example.order_final_project_java_phincon.orders.OrderService;
import com.example.order_final_project_java_phincon.utils.helper.DtoMessageKafka;
import com.example.order_final_project_java_phincon.utils.helper.ObjectMapperConvert;
import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderListenerEvent {

    private final OrderService orderService;
    private final ObjectMapperConvert objectMapperConvert;


    @KafkaListener(topics = "topicOrder")
    public void eventCreateOrderListener(String request){

        log.info("eventCreateOrderListener == {}", request);


        DtoMessageKafka requestCreateOrder = objectMapperConvert.convertFromJson(request, DtoMessageKafka.class);
        log.info("eventCreateOrderListener == {}", requestCreateOrder);

//        orderService.save(requestCreateOrder.getData()).subscribe();
    }

    @KafkaListener(topics = "TOPIC-PRODUCT")
    public void eventsendMessageToProudctTopic(String message){



    }


}
