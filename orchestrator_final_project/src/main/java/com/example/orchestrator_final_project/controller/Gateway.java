package com.example.orchestrator_final_project.controller;

import com.example.orchestrator_final_project.service.StepFlowServices;
import com.example.orchestrator_final_project.utils.request.RequestCreateOrder;
import com.example.orchestrator_final_project.utils.response.BaseResponse;
import com.example.orchestrator_final_project.utils.response.DtoMessageKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/gateway")
public class Gateway {


    private final StepFlowServices services;

    @PostMapping("/createOrder")
    public BaseResponse<RequestCreateOrder> createOrder(@RequestBody RequestCreateOrder request){

        DtoMessageKafka requestCreateOrderDtoMessageKafka = new DtoMessageKafka();
//        requestCreateOrderDtoMessageKafka.setData(request);

//        services.eventCreateOrder(requestCreateOrderDtoMessageKafka);
        log.info("PROCESS IN GATEWAY SUCCESS");

        return null;
    }

//    @PostMapping("/createOrder")
//    public BaseResponse<RequestCreateOrder> createOrderWebClient(@RequestBody RequestCreateOrder request){
//
//        serviceOrchestrator.createOrderService(request);
//
//        log.info("PROCESS IN GATEWAY SUCCESS");
//
//        return null;
//    }
}
