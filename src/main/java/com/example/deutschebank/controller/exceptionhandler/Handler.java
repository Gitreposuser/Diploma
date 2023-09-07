package com.example.deutschebank.controller.exceptionhandler;

import com.example.deutschebank.exception.BadEmailException;
import com.example.deutschebank.exception.BadOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class Handler {
    @ExceptionHandler(BadEmailException.class)
    public ResponseEntity<String> handleBadEmailException() {
        log.error("Cannot update entity, email already exist in database!");
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(BadOperationException.class)
    public ResponseEntity<String> handleBadOperationException() {
        log.error("Bad operation exception!");
        return ResponseEntity.unprocessableEntity().build();
    }
}
