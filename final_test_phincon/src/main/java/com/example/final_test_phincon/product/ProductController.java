package com.example.final_test_phincon.product;

import com.example.final_test_phincon.utils.helper.RequestForValidation;
import com.example.final_test_phincon.utils.request.RequestCreateProduct;
import com.example.final_test_phincon.utils.request.RequestDeduct;
import com.example.final_test_phincon.utils.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/create")
    public Mono<BaseResponse<Product>> createProduct(@RequestBody RequestCreateProduct request) {
        Mono<BaseResponse<Product>> result = service.save(request);


        return result;
    }

    @GetMapping("/all")
    public Flux<BaseResponse<Product>> getAllProducts() {

        Flux<BaseResponse<Product>> result = service.getAllProducts();
        return result;
    }

    @GetMapping("/{id}")
    public Mono<BaseResponse<Product>> getProductById(@PathVariable int id) {

        Mono<BaseResponse<Product>> result = service.getProductById(id);

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

        Mono<BaseResponse<?>> result = service.deleteProduct(id);

        return result;
    }

    @PutMapping("/validationProduct")
    public Mono<BaseResponse<Product>> validatePeoduct(@RequestBody RequestForValidation request){

        Mono<BaseResponse<Product>> result = service.validationProductFromMessageOrder(request);

        return result;
    }

    @PutMapping("/deduct")
    public Mono<BaseResponse<Product>> deduct(@RequestBody Product requestDeduct){

        Mono<BaseResponse<Product>> result = service.updateProduct(requestDeduct);

        return result;
    }
}
