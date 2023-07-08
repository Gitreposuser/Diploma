package com.example.deutschebank.controller;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PostMapping(value = "/get/emitter/by/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<List<Transaction>>>
    getTransactionsByEmitterIBAN(@PathVariable("iban") String iban) {
        Optional<List<Transaction>> list =
                transactionService.getTransactionsByEmitterIBAN(iban);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping(value = "/get/receiver/by/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<List<Transaction>>>
    getTransactionsByReceiverIBAN(@PathVariable("iban") String iban) {
        Optional<List<Transaction>> list =
                transactionService.getTransactionsByReceiverIBAN(iban);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping(value = "/get/by/amount-range/{from}/{to}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<List<Transaction>>>
    getTransactionsByAmountInRange(@PathVariable("from") Integer from,
                                   @PathVariable("to") Integer to) {
        Optional<List<Transaction>> list =
                transactionService.getTransactionsByAmountInRange(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping(value = "/get/by/date-range/{from}/{to}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<List<Transaction>>>
    getTransactionsByAmountInRange(@PathVariable("from") LocalDateTime from,
                                   @PathVariable("to") LocalDateTime to) {
        Optional<List<Transaction>> list =
                transactionService.getTransactionsByDateInRange(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}