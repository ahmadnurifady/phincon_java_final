package com.example.cart_final_project_java_phincon.exeption;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }
}
