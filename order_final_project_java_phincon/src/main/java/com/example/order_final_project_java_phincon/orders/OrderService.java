package com.example.order_final_project_java_phincon.orders;

import com.example.order_final_project_java_phincon.exeption.OrderNotFoundException;
import com.example.order_final_project_java_phincon.kafka.ProducerOrder;
import com.example.order_final_project_java_phincon.orderItem.OrderItem;
import com.example.order_final_project_java_phincon.orderItem.OrderItemRepository;
import com.example.order_final_project_java_phincon.utils.helper.DtoMessageKafka;
import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrderItem;
import com.example.order_final_project_java_phincon.utils.response.CreateOrderResponse;
import com.example.order_final_project_java_phincon.utils.response.KafkaResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrder;
import com.example.order_final_project_java_phincon.utils.response.BaseResponse;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final OrderItemRepository orderItemRepository;

    private final ProducerOrder producerOrder;

    public Mono<BaseResponse<CreateOrderResponse>> save(RequestCreateOrder request) {

        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .orderDate(LocalDateTime.now())
                .billingAddress(request.getBillingAddress())
                .orderStatus("PENDING")
                .paymentMethod(request.getPaymentMethod())
                .shippingAddress(request.getShippingAddress())
                .totalAmount(0)
                .build();

        return repository.save(order)
                .doOnSuccess(savedOrder -> log.info("CREATE ORDER SUCCESS == {}", savedOrder))
                .flatMap(savedOrder -> {

                    OrderItem orderItem = OrderItem.builder()
                            .price(0)
                            .productId(request.getOrderItem().getProductId())
                            .quantity(request.getOrderItem().getQuantity())
                            .orderId(savedOrder.getId())
                            .build();

                    CreateOrderResponse orderResponse = CreateOrderResponse.builder()
                            .order(savedOrder)
                            .orderItem(orderItem)
                            .build();

                    return orderItemRepository.save(orderItem)
                            .doOnSuccess(savedOrderItem -> {
                                log.info("SUCCESS CREATE ORDER ITEM == {}", savedOrderItem);

                                DtoMessageKafka dtoMessageKafka = DtoMessageKafka.builder()
                                        .statusSaga("CREATE-ORDER-SUCCESS")
                                        .data(orderResponse)
                                        .build();

                                producerOrder.sendMessageSagaFlow(dtoMessageKafka);
                            })
                            .thenReturn(new BaseResponse<>(
                                    HttpStatus.CREATED,
                                    "Order is Created",
                                    orderResponse
                            ));
                });
    }

    public Mono<BaseResponse<CreateOrderResponse>> updateOrderAndOrderItem(CreateOrderResponse updateRequest) {
        return repository.findById(updateRequest.getOrder().getId())
                .flatMap(existingOrder -> {
                    // Update Order fields
                    if (updateRequest.getOrder().getCustomerId() != null) {
                        existingOrder.setCustomerId(updateRequest.getOrder().getCustomerId());
                    }
                    if (updateRequest.getOrder().getOrderDate() != null) {
                        existingOrder.setOrderDate(updateRequest.getOrder().getOrderDate());
                    }
                    if (updateRequest.getOrder().getBillingAddress() != null) {
                        existingOrder.setBillingAddress(updateRequest.getOrder().getBillingAddress());
                    }
                    if (updateRequest.getOrder().getOrderStatus() != null) {
                        existingOrder.setOrderStatus(updateRequest.getOrder().getOrderStatus());
                    }
                    if (updateRequest.getOrder().getPaymentMethod() != null) {
                        existingOrder.setPaymentMethod(updateRequest.getOrder().getPaymentMethod());
                    }
                    if (updateRequest.getOrder().getShippingAddress() != null) {
                        existingOrder.setShippingAddress(updateRequest.getOrder().getShippingAddress());
                    }
                    if (updateRequest.getOrder().getTotalAmount() != 0) {
                        existingOrder.setTotalAmount(updateRequest.getOrder().getTotalAmount());
                    }

                    return repository.save(existingOrder)
                            .flatMap(updatedOrder ->
                                    orderItemRepository.findById(updateRequest.getOrderItem().getId())
                                            .flatMap(existingOrderItem -> {
                                                // Update OrderItem fields
                                                if (updateRequest.getOrderItem().getPrice() != 0) {
                                                    existingOrderItem.setPrice(updateRequest.getOrderItem().getPrice());
                                                }
                                                if (updateRequest.getOrderItem().getProductId() != null) {
                                                    existingOrderItem.setProductId(updateRequest.getOrderItem().getProductId());
                                                }
                                                if (updateRequest.getOrderItem().getQuantity() != null) {
                                                    existingOrderItem.setQuantity(updateRequest.getOrderItem().getQuantity());
                                                }
                                                return orderItemRepository.save(existingOrderItem)
                                                        .then(Mono.just(updatedOrder)); // Return the updated order
                                            })
                                            .switchIfEmpty(Mono.error(new OrderNotFoundException(String.format("OrderItem not found. ID: %s", updateRequest.getOrderItem().getId()))))
                            );
                })
                .switchIfEmpty(Mono.error(new OrderNotFoundException(String.format("Order not found. ID: %s", updateRequest.getOrder().getId()))))
                .map(updatedOrder -> new BaseResponse<>(
                        HttpStatus.OK,
                        "Order and OrderItem updated successfully",
                        updateRequest
                ));
    }
    public Flux<BaseResponse<Order>> getAllOrders() {
        return repository.findAll()
                .flatMap(order -> Flux.just(new BaseResponse<>(
                        HttpStatus.OK,
                        "Find All Order Success",
                        order
                )));
    }

    public Mono<BaseResponse<Order>> getOrderById(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new OrderNotFoundException(String.format("Product not found. ID: %s", id))))
                .flatMap(order -> Mono.just(new BaseResponse<>(
                        HttpStatus.OK,
                        "Success Find Order By Id",
                        order
                )));

    }

    public Mono<BaseResponse<?>> deleteOrder(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new OrderNotFoundException(String.format("Order not found. ID: %s", id))))
                .flatMap(value -> repository.deleteById(id)
                        .then(Mono.just(new BaseResponse<>(
                                HttpStatus.OK,
                                "Order Success Delete",
                                null
                        ))));
    }

}
