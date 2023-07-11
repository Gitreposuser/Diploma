package com.example.deutschebank.exception;

/**
 * Custom error exception generation
 */
public class BadOperationException extends RuntimeException{
    public BadOperationException(String message) {
        super(message);
    }
}
