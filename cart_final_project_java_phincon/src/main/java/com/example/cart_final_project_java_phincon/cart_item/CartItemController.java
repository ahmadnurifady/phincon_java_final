package com.example.cart_final_project_java_phincon.cart_item;


import com.example.cart_final_project_java_phincon.utils.request.RequestCreateCartItem;
import com.example.cart_final_project_java_phincon.utils.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cartItems")
public class CartItemController {

    @Autowired
    private CartItemService service;

    @PostMapping("/create")
    public Mono<BaseResponse<CartItem>> createCartItems(@RequestBody RequestCreateCartItem request) {
        Mono<BaseResponse<CartItem>> result = service.save(request);

        return result;
    }

    @GetMapping("/all")
    public Flux<BaseResponse<CartItem>> getAllCartItems() {

        Flux<BaseResponse<CartItem>> result = service.getAllCartItems();
        return result;
    }

    @GetMapping("/{id}")
    public Mono<BaseResponse<CartItem>> getCartItemById(@PathVariable int id) {

        Mono<BaseResponse<CartItem>> result = service.getcartItemById(id);

        return result;
    }

//    @PutMapping("/update/{id}")
//    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable int id, @RequestBody Product product) {
//        return service.updateProduct(id, product)
//                .map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @DeleteMapping("/delete/{id}")
    public Mono<BaseResponse<?>> deleteCartItem(@PathVariable int id) {

        Mono<BaseResponse<?>> result = service.deleteCartItem(id);

        return result;
    }
}
