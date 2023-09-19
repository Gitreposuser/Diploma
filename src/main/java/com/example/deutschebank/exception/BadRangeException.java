package com.example.deutschebank.exception;

public class BadRangeException extends RuntimeException{
    public BadRangeException(String message) {
        super(message);
    }
}
