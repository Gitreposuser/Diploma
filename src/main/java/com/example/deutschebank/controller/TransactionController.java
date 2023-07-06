package com.example.deutschebank.controller;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        log.info("start creating transaction");
        transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @PostMapping(value = "/get-all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<List<Transaction>>> getAllTransactions() {
        Optional<List<Transaction>> list = transactionService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}