package com.example.order_final_project_java_phincon.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import com.example.order_final_project_java_phincon.utils.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleBookNotFoundException(OrderNotFoundException ex) {

        Mono<ErrorResponse> errResponse = Mono.just(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));

        return errResponse;
    }
}
