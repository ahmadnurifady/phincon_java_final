package com.example.payment_final_project_java_phincon.exeption;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
}

