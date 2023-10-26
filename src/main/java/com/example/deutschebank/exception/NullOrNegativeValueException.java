package com.example.deutschebank.exception;

public class NullOrNegativeValueException extends RuntimeException {
    public NullOrNegativeValueException(String message) {
        super(message);
    }
}
