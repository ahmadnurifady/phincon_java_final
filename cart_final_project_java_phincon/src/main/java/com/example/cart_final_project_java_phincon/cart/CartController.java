package com.example.cart_final_project_java_phincon.cart;

import com.example.cart_final_project_java_phincon.utils.request.RequestCreateCart;
import com.example.cart_final_project_java_phincon.utils.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/carts")
public class CartController {


    @Autowired
    private CartService service;

    @PostMapping("/create")
    public Mono<BaseResponse<Cart>> createCart(@RequestBody RequestCreateCart request) {
        Mono<BaseResponse<Cart>> result = service.save(request);

        return result;
    }

    @GetMapping("/all")
    public Flux<BaseResponse<Cart>> getAllCarts() {

        Flux<BaseResponse<Cart>> result = service.getAllCarts();
        return result;
    }

    @GetMapping("/{id}")
    public Mono<BaseResponse<Cart>> getCartById(@PathVariable int id) {

        Mono<BaseResponse<Cart>> result = service.getcartById(id);

        return result;
    }

//    @PutMapping("/update/{id}")
//    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable int id, @RequestBody Product product) {
//        return service.updateProduct(id, product)
//                .map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @DeleteMapping("/delete/{id}")
    public Mono<BaseResponse<?>> deleteProduct(@PathVariable int id) {

        Mono<BaseResponse<?>> result = service.deleteCart(id);

        return result;
    }


}
