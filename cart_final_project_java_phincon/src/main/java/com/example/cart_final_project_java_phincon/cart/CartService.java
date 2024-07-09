package com.example.cart_final_project_java_phincon.cart;

import com.example.cart_final_project_java_phincon.exeption.CartNotFoundException;
import com.example.cart_final_project_java_phincon.utils.request.RequestCreateCart;
import com.example.cart_final_project_java_phincon.utils.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class CartService {


    @Autowired
    private CartRepository repository;

    public Mono<BaseResponse<Cart>> save(RequestCreateCart request) {

        Cart cart = Cart.builder()
                .status(request.getStatus())
                .totalPrice(request.getTotalPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return repository.save(cart)
                .then(Mono.just(new BaseResponse<>(
                        HttpStatus.CREATED,
                        "Cart is Created",
                        cart
                )));
    }

    public Flux<BaseResponse<Cart>> getAllCarts() {
        return repository.findAll()
                .flatMap(cart -> {
                    return Flux.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Find All Cart Success",
                            cart
                    ));
                });
    }

    public Mono<BaseResponse<Cart>> getcartById(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CartNotFoundException(String.format("Cart not found. ID: %s", id))))
                .flatMap(cart ->{
                    return Mono.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Success Find Cart By Id",
                            cart
                    ));
                });

    }

    public Mono<BaseResponse<?>> deleteCart(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CartNotFoundException(String.format("Cart not found. ID: %s", id))))
                .flatMap(value -> repository.deleteById(id)
                        .then(Mono.just(new BaseResponse<>(
                                HttpStatus.OK,
                                "Cart Success Delete",
                                null
                        ))));
    }

}
