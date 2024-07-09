package com.example.order_final_project_java_phincon.orderItem;


import com.example.order_final_project_java_phincon.utils.request.RequestCreateOrderItem;
import com.example.order_final_project_java_phincon.utils.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService service;

    @PostMapping("/create")
    public Mono<BaseResponse<OrderItem>> createOrderItem(@RequestBody RequestCreateOrderItem request) {
        Mono<BaseResponse<OrderItem>> result = service.save(request);

        return result;
    }

    @GetMapping("/all")
    public Flux<BaseResponse<OrderItem>> getAllOrderItems() {

        Flux<BaseResponse<OrderItem>> result = service.getAllOrderItems();
        return result;
    }

    @GetMapping("/{id}")
    public Mono<BaseResponse<OrderItem>> getOrderItemById(@PathVariable int id) {

        Mono<BaseResponse<OrderItem>> result = service.getOrderItemById(id);

        return result;
    }

//    @PutMapping("/update/{id}")
//    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable int id, @RequestBody Product product) {
//        return service.updateProduct(id, product)
//                .map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @DeleteMapping("/delete/{id}")
    public Mono<BaseResponse<?>> deleteOrderItem(@PathVariable int id) {

        Mono<BaseResponse<?>> result = service.deleteOrderItem(id);

        return result;
    }

}
