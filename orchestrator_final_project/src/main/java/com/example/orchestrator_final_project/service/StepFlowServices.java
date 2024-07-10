package com.example.orchestrator_final_project.service;

import com.example.orchestrator_final_project.kafkaOrcherstrator.consume.OrchestratorConsume;
import com.example.orchestrator_final_project.kafkaOrcherstrator.producer.OrchestratorProducer;
import com.example.orchestrator_final_project.utils.helper.ObjectMapperConvert;
import com.example.orchestrator_final_project.utils.helper.model.Order;
import com.example.orchestrator_final_project.utils.helper.model.Product;
import com.example.orchestrator_final_project.utils.request.*;
import com.example.orchestrator_final_project.utils.response.BaseResponse;
import com.example.orchestrator_final_project.utils.response.DtoMessageKafka;
import com.example.orchestrator_final_project.webClient.BalanceWebClient;
import com.example.orchestrator_final_project.webClient.OrderWebClient;
import com.example.orchestrator_final_project.webClient.ProductWebClient;
import com.example.orchestrator_final_project.webClient.TransactionWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class StepFlowServices {

    private final OrchestratorProducer orchestratorProducer;

    private final ObjectMapperConvert objectMapperConvert;

    private final ProductWebClient productWebClient;

    private final OrderWebClient orderWebClient;

    private final BalanceWebClient balanceWebClient;

    private final TransactionWebClient transactionWebClient;

    @KafkaListener(topics = "SAGA-FLOW")
    public void listenerEventSagaFlow(String msg) {

        DtoMessageKafka getEvent = objectMapperConvert.convertFromJson(msg, DtoMessageKafka.class);

        log.info("DATA GET EVENT == {}", getEvent);

        switch (getEvent.getStatusSaga()) {

            case "CREATE-ORDER-SUCCESS": {
                eventValidationProductAndQuantity(getEvent).subscribe();
                break;
            }
            case "ERROR-VALIDATE-PRODUCT": {
                eventRollbackValidationProduct(getEvent).subscribe();
                break;
            }

        }

    }


    public Mono<Void> eventValidationProductAndQuantity(DtoMessageKafka message) {

        RequestForValidation validation = RequestForValidation.builder()
                .productId(message.getData().getOrderItem().getProductId())
                .quantity(message.getData().getOrderItem().getQuantity())
                .build();

        log.info("PROCESS VALIDATION PRODUCT");
        return productWebClient.validationProduct(validation)

                .flatMap(product -> {

                    log.info("RESULT PRODUCT VALIDATION == {}", product);

                    float totalAmountOrder = message.getData().getOrderItem().getQuantity() * product.getData().getPrice();

                    log.info("TOTAL AMOUNT == {}", totalAmountOrder);

                    CreateOrderResponse createOrderResponse = CreateOrderResponse.builder()
                            .order(Order.builder()
                                    .id(message.getData().getOrder().getId())
                                    .totalAmount(totalAmountOrder)
                                    .build())
                            .orderItem(OrderItem.builder()
                                    .id(message.getData().getOrderItem().getId())
                                    .price(product.getData().getPrice())
                                    .build())
                            .build();

                    log.info("DATA UNTUK INPUT ORDER UPDATE == {}", createOrderResponse);

                    return orderWebClient.updateOrder(createOrderResponse)
                            .flatMap(updateOrder -> {

                                log.info("UPDATED ORDER == {}", updateOrder);

                                log.info("UPDATED ORDER SUCCESS");

                                RequestValidateCustomerBalance requestPayment = RequestValidateCustomerBalance.builder()
                                        .customerId(message.getData().getOrder().getCustomerId())
                                        .amount(totalAmountOrder)
                                        .build();


                                log.info("MASUK PROCESS PAYMENT");
                                return balanceWebClient.payment(requestPayment)
                                        .flatMap(payment -> {

                                            log.info("SUCCESS PAYMENT");

                                            log.info("DATA PAYMENT =={}", payment);

                                            RequestCreateTransaction requestCreateTransaction = RequestCreateTransaction.builder()
                                                    .amount(totalAmountOrder)
                                                    .orderId(message.getData().getOrder().getId())
                                                    .build();

//                                            return transactionWebClient.createTransaction(requestCreateTransaction)
//                                                    .flatMap(transaction -> {
//
//                                                        log.info("SUCCESS CREATE TRANSACTION");
//                                                        log.info("DATA CREATE TRANSACTION== {}", transaction);
//
//                                                        return Mono.empty();
//                                                    });

                                            CreateOrderResponse updateOrderToComplete = CreateOrderResponse.builder()
                                                    .order(Order.builder()
                                                            .id(message.getData().getOrder().getId())
                                                            .orderStatus("COMPLETED")
                                                            .build())
                                                    .orderItem(OrderItem.builder()
                                                            .id(message.getData().getOrderItem().getId())
                                                            .build())
                                                    .build();


                                            return orderWebClient.updateOrder(updateOrderToComplete)
                                                    .flatMap(orderUpdateComplete -> {
                                                        log.info("UPDATE ORDER COMPLETED");
                                                        log.info("UPDATE ORDER COMPLETED DATA == {}", orderUpdateComplete);

                                                        return transactionWebClient.createTransaction(requestCreateTransaction)
                                                                .flatMap(transaction -> {

                                                                    log.info("SUCCESS CREATE TRANSACTION");
                                                                    log.info("DATA CREATE TRANSACTION== {}", transaction);

                                                                    return Mono.empty();
                                                                });
                                                    });
                                        })
                                        .doOnError(errorPayment -> {
                                            log.info("ROLLBACK CAUSE PAYMENT");

                                            CreateOrderResponse update = CreateOrderResponse.builder()
                                                    .order(Order.builder()
                                                            .id(message.getData().getOrder().getId())
                                                            .orderStatus("CANCELED")
                                                            .build())
                                                    .orderItem(OrderItem.builder()
                                                            .id(message.getData().getOrderItem().getId())
                                                            .build())
                                                    .build();

                                            orderWebClient.updateOrder(update)
                                                    .doOnSuccess(updateOrderCauseProductValidation -> {
                                                        log.info("ROLLBACK ORDER CAUSE PAYMENT IS SUCCESS");
                                                    })
                                                    .subscribe();

                                            Product requestDeduct = Product.builder()
                                                    .id(message.getData().getOrderItem().getProductId())
                                                    .stockQuantity(message.getData().getOrderItem().getQuantity())
                                                    .build();

                                            productWebClient.deduct(requestDeduct)
                                                    .doOnSuccess(deductProduct -> {
                                                        log.info("DEDUCT PRODUCT SUCCESS");
                                                    })
                                                    .subscribe();
                                        })
                                        .then();

                            });



                })
                .doOnError(error -> {

                    log.info("ROLLBACK CAUSE PRODUCT VALIDATE");

                    CreateOrderResponse createOrderResponse = CreateOrderResponse.builder()
                            .order(Order.builder()
                                    .id(message.getData().getOrder().getId())
                                    .orderStatus("CANCELED")
                                    .build())
                            .orderItem(OrderItem.builder()
                                    .id(message.getData().getOrderItem().getId())
                                    .build())
                            .build();

                    orderWebClient.updateOrder(createOrderResponse)
                            .doOnSuccess(updateOrderCauseProductValidation -> {
                                log.info("ROLLBACK ORDER CAUSE PRODUCT VALIDATION IS SUCCESS");
                            })
                            .subscribe();
                    log.info("error aja == {}", error.getMessage());
                }).then();


    }

    public Mono<BaseResponse<CreateOrderResponse>> eventRollbackValidationProduct(DtoMessageKafka message) {

        CreateOrderResponse createOrderResponse = CreateOrderResponse.builder()
                .order(Order.builder()
                        .id(message.getData().getOrder().getId())
                        .orderStatus("CANCELED")
                        .build())
                .orderItem(OrderItem.builder()
                        .id(message.getData().getOrderItem().getId())
                        .build())
                .build();

        return orderWebClient.updateOrder(createOrderResponse)
                .flatMap(updateOrder -> {
                    log.info("SUCCESS ROLBACK ORDER");

                    log.info("DATA UPDATED ROLLBACK ORDER == {}", updateOrder);
                    return null;
                });
    }


}
