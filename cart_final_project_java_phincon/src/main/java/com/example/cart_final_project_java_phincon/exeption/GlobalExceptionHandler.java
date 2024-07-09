package com.example.cart_final_project_java_phincon.exeption;

import com.example.cart_final_project_java_phincon.utils.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleBookNotFoundException(CartNotFoundException ex) {

        Mono<ErrorResponse> errResponse = Mono.just(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));

        return errResponse;
    }

}
