package com.example.deutschebank.exception;

public class BadEmailException extends RuntimeException {
    public BadEmailException(String message) {
        super(message);
    }
}
