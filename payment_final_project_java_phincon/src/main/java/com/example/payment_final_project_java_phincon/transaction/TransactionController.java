package com.example.payment_final_project_java_phincon.transaction;

import com.example.payment_final_project_java_phincon.utils.request.RequestCreateTransaction;
import com.example.payment_final_project_java_phincon.utils.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {


    @Autowired
    private TransacationService service;

    @PostMapping("/create")
    public Mono<BaseResponse<Transaction>> createTransaction(@RequestBody RequestCreateTransaction request) {
        Mono<BaseResponse<Transaction>> result = service.save(request);

        return result;
    }

    @GetMapping("/all")
    public Flux<BaseResponse<Transaction>> getAllTransactions() {

        Flux<BaseResponse<Transaction>> result = service.getAllTransactions();
        return result;
    }

    @GetMapping("/{id}")
    public Mono<BaseResponse<Transaction>> getTransactionById(@PathVariable int id) {

        Mono<BaseResponse<Transaction>> result = service.getTransactionById(id);

        return result;
    }

//    @PutMapping("/update/{id}")
//    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable int id, @RequestBody Product product) {
//        return service.updateProduct(id, product)
//                .map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @DeleteMapping("/delete/{id}")
    public Mono<BaseResponse<?>> deleteTransaction(@PathVariable int id) {

        Mono<BaseResponse<?>> result = service.deleteTransaction(id);

        return result;
    }
}
