package com.example.deutschebank.exception;

public class NullOrNotExistException extends RuntimeException {
    public NullOrNotExistException(String message) {
        super(message);
    }
}
