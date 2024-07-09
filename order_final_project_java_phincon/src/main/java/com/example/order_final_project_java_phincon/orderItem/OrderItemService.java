package com.example.order_final_project_java_phincon.orderItem;

import com.example.order_final_project_java_phincon.exeption.OrderNotFoundException;
import com.example.order_final_project_java_phincon.orders.Order;
import com.example.order_final_project_java_phincon.orders.OrderRepository;
import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrder;
import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrderItem;
import com.example.order_final_project_java_phincon.utils.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public Mono<BaseResponse<OrderItem>> save(RequestCreateOrderItem request) {

        OrderItem orderItem = OrderItem.builder()
                .price(0)
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .orderId(request.getOrderId())
                .build();

        return repository.save(orderItem)
                .then(Mono.just(new BaseResponse<>(
                        HttpStatus.CREATED,
                        "OrderItem is Created",
                        orderItem
                )));
    }

    public Flux<BaseResponse<OrderItem>> getAllOrderItems() {
        return repository.findAll()
                .flatMap(orderItem -> {
                    return Flux.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Find All OrderItems Success",
                            orderItem
                    ));
                });
    }

    public Mono<BaseResponse<OrderItem>> getOrderItemById(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new OrderNotFoundException(String.format("OrderItem not found. ID: %s", id))))
                .flatMap(orderItem ->{
                    return Mono.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Success Find OrderItem By Id",
                            orderItem
                    ));
                });

    }

    public Mono<BaseResponse<?>> deleteOrderItem(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new OrderNotFoundException(String.format("OrderItem not found. ID: %s", id))))
                .flatMap(value -> repository.deleteById(id)
                        .then(Mono.just(new BaseResponse<>(
                                HttpStatus.OK,
                                "OrderItem Success Delete",
                                null
                        ))));
    }
}
