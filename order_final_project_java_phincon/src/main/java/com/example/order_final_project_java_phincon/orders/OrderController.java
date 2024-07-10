package com.example.order_final_project_java_phincon.orders;

import com.example.order_final_project_java_phincon.utils.response.CreateOrderResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrder;
import com.example.order_final_project_java_phincon.utils.response.BaseResponse;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public Mono<BaseResponse<CreateOrderResponse>> createOrder(@RequestBody RequestCreateOrder request) {
        return service.save(request);

    }

    @GetMapping("/all")
    public Flux<BaseResponse<Order>> getAllOrders() {

        Flux<BaseResponse<Order>> result = service.getAllOrders();
        return result;
    }

    @GetMapping("/{id}")
    public Mono<BaseResponse<Order>> getOrderById(@PathVariable int id) {

        Mono<BaseResponse<Order>> result = service.getOrderById(id);

        return result;
    }

    @PutMapping("/update")
    public Mono<BaseResponse<CreateOrderResponse>> updateOrderAndOrderItem(@RequestBody CreateOrderResponse request) {
        return service.updateOrderAndOrderItem(request);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<BaseResponse<?>> deleteOrder(@PathVariable int id) {

        Mono<BaseResponse<?>> result = service.deleteOrder(id);

        return result;
    }
}
