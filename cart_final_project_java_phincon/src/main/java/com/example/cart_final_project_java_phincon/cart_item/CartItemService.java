package com.example.cart_final_project_java_phincon.cart_item;

import com.example.cart_final_project_java_phincon.cart.Cart;
import com.example.cart_final_project_java_phincon.cart.CartRepository;
import com.example.cart_final_project_java_phincon.exeption.CartNotFoundException;
import com.example.cart_final_project_java_phincon.utils.request.RequestCreateCart;
import com.example.cart_final_project_java_phincon.utils.request.RequestCreateCartItem;
import com.example.cart_final_project_java_phincon.utils.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class CartItemService {
    @Autowired
    private CartItemRepository repository;

    public Mono<BaseResponse<CartItem>> save(RequestCreateCartItem request) {

        CartItem cartItem = CartItem.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .cartId(request.getCartId())
                .build();

        return repository.save(cartItem)
                .then(Mono.just(new BaseResponse<>(
                        HttpStatus.CREATED,
                        "CartItem is Created",
                        cartItem
                )));
    }

    public Flux<BaseResponse<CartItem>> getAllCartItems() {
        return repository.findAll()
                .flatMap(cartItem -> {
                    return Flux.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Find All CartItems Success",
                            cartItem
                    ));
                });
    }

    public Mono<BaseResponse<CartItem>> getcartItemById(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CartNotFoundException(String.format("CartItem not found. ID: %s", id))))
                .flatMap(cartItem ->{
                    return Mono.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Success Find Cart By Id",
                            cartItem
                    ));
                });

    }

    public Mono<BaseResponse<?>> deleteCartItem(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CartNotFoundException(String.format("CartItem not found. ID: %s", id))))
                .flatMap(value -> repository.deleteById(id)
                        .then(Mono.just(new BaseResponse<>(
                                HttpStatus.OK,
                                "CartItem Success Delete",
                                null
                        ))));
    }

}
