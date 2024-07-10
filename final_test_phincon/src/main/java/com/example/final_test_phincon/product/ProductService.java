package com.example.final_test_phincon.product;

import com.example.final_test_phincon.exeption.ProductNotFoundException;
import com.example.final_test_phincon.kafka.ProductProducer;
import com.example.final_test_phincon.utils.helper.CreateOrderResponse;
import com.example.final_test_phincon.utils.helper.RequestForValidation;
import com.example.final_test_phincon.utils.helper.ValidationService;
import com.example.final_test_phincon.utils.request.*;
import com.example.final_test_phincon.utils.response.BaseResponse;
import com.example.final_test_phincon.utils.response.DtoMessageKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository repository;

    private final ValidationService validationService;

    public Mono<BaseResponse<Product>> save(RequestCreateProduct request) {

       return validationService.validate(request).flatMap(valid -> {
           Product product = Product.builder()
                   .name(request.getName())
                   .price(request.getPrice())
                   .category(request.getCategory())
                   .description(request.getDescription())
                   .stockQuantity(request.getStockQuantity())
                   .imageUrl(request.getImageUrl())
                   .createdAt(Instant.now())
                   .updatedAt(Instant.now())
                   .build();

           return repository.save(product)
                   .then(Mono.just(new BaseResponse<>(
                           HttpStatus.CREATED,
                           "Product is Created",
                           product
                   )));
       });
    }

    public Flux<BaseResponse<Product>> getAllProducts() {
        return repository.findAll()
                .flatMap(product -> {
                    return Flux.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Find All Product Success",
                            product
                    ));
                });
    }

    public Mono<BaseResponse<Product>> getProductById(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(String.format("Product not found. ID: %s", id))))
                .flatMap(product -> {
                    return Mono.just(new BaseResponse<>(
                            HttpStatus.OK,
                            "Success Find Product By Id",
                            product
                    ));
                });

    }

    public Mono<BaseResponse<Product>> updateProduct(Product updateRequest) {
        return repository.findById(updateRequest.getId())
                .flatMap(existingProduct -> {
                    // Update fields only if they are not null
                    if (updateRequest.getName() != null) {
                        existingProduct.setName(updateRequest.getName());
                    }
                    if (updateRequest.getPrice() != 0.0f) {
                        existingProduct.setPrice(updateRequest.getPrice());
                    }
                    if (updateRequest.getCategory() != null) {
                        existingProduct.setCategory(updateRequest.getCategory());
                    }
                    if (updateRequest.getDescription() != null) {
                        existingProduct.setDescription(updateRequest.getDescription());
                    }
                    if (updateRequest.getImageUrl() != null) {
                        existingProduct.setImageUrl(updateRequest.getImageUrl());
                    }
                    if (updateRequest.getStockQuantity() != 0) {
                        existingProduct.setStockQuantity(updateRequest.getStockQuantity() + existingProduct.getStockQuantity());
                    }
                    existingProduct.setUpdatedAt(Instant.now());

                    return repository.save(existingProduct);
                })
                .map(updatedProduct -> new BaseResponse<>(
                        HttpStatus.OK,
                        "Product updated successfully",
                        updatedProduct
                ))
                .switchIfEmpty(Mono.error(new ProductNotFoundException(String.format("Product not found. ID: %d", updateRequest.getId()))));
    }


    public Mono<BaseResponse<?>> deleteProduct(int id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(String.format("Product not found. ID: %s", id))))
                .flatMap(value -> repository.deleteById(id)
                        .then(Mono.just(new BaseResponse<>(
                                HttpStatus.OK,
                                "Product Success Delete",
                                null
                        ))));
    }

    public Mono<BaseResponse<Product>> validationProductFromMessageOrder(RequestForValidation request) {

        return repository.findById(request.getProductId())
                .switchIfEmpty(Mono.defer(() -> {

                    log.info("PRODUCT NOT FOUND, ROLLBACK");
//                    producer.sendMessageToOrchestrator(new DtoMessageKafka("ERROR-VALIDATE-PRODUCT", new CreateOrderResponse(new Order(), new OrderItem())));


                    return Mono.error(new ProductNotFoundException(String.format("Product not found. ID: %s", request.getProductId())));
                }))
                .flatMap(product -> {
                    log.info("DATA IS == {}", product);

                    log.info("PRODUCT IS EXIST");

                    if (product.getStockQuantity() >= request.getQuantity()) {

                        log.info("HASIL PENGURANGAN QUANTITY == {}", product.getStockQuantity() - request.getQuantity());
                        int stockUpdate = product.getStockQuantity() - request.getQuantity();

                        product.setStockQuantity(stockUpdate);

                        log.info("SEBELUM UPDATE QUANTITY == {}", product);
                        return repository.save(product)
                                .map(updatedProduct -> new BaseResponse<>(
                                        HttpStatus.OK,
                                        "Product updated successfully",
                                        updatedProduct
                                ));

                    } else {

                        log.info("STOCK PRODUCT TIDAK MENCUKUPI, ROLLBACK");
//                        producer.sendMessageToOrchestrator(new DtoMessageKafka("ERROR-VALIDATE-PRODUCT", new CreateOrderResponse(new Order(), new OrderItem())));

                        return Mono.error(new Exception(String.format("STOCK OUT OF QUANTITY")));

                    }
                });


    }
}
