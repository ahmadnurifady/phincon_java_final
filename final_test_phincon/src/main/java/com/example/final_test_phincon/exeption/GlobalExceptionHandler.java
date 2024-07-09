package com.example.final_test_phincon.exeption;

import com.example.final_test_phincon.utils.response.BaseResponse;
import com.example.final_test_phincon.utils.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<BaseResponse> dataNotFoundException(ProductNotFoundException ex) {

        Mono<ErrorResponse> errResponse = Mono.just(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));

        Mono<BaseResponse> error = Mono.just(new BaseResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                "ERROR-VALIDATE-PRODUCT"
        ));

        return error;
    }
}
