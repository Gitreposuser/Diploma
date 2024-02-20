package com.example.deutschebank.exception.exceptionhandler;

import com.example.deutschebank.exception.BadEmailException;
import com.example.deutschebank.exception.BadOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BadEmailException.class)
    public ResponseEntity<String> handleBadEmailException() {
        log.error("Cannot update entity, email already exist in database!");
        return ResponseEntity.unprocessableEntity().build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadOperationException.class)
    public ResponseEntity<String> handleBadOperationException() {
        log.error("Bad operation exception!");
        return ResponseEntity.unprocessableEntity().build();
    }
}
