package com.example.deutschebank.controller;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class for handling transactions.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    /**
     * Creates a new transaction.
     *
     * @param transaction the transaction to be created
     * @return the created transaction
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        log.info("start creating transaction");
        transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions
     */
    @PostMapping(value = "/get-all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> list = transactionService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * Retrieves transactions by emitter IBAN.
     *
     * @param iban the emitter IBAN to search for
     * @return a list of transactions with the given emitter IBAN
     */
    @PostMapping(value = "/get/emitter/by-iban/{iban}")
    public ResponseEntity<List<Transaction>>
    getTransactionsByEmitterIBAN(@PathVariable("iban") String iban) {
        List<Transaction> list =
                transactionService.getTransactionsByEmitterIBAN(iban);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * Retrieves transactions by receiver IBAN.
     *
     * @param iban the receiver IBAN to search for
     * @return a list of transactions with the given receiver IBAN
     */
    @GetMapping(value = "/get/receiver/by-iban/{iban}")
    public ResponseEntity<List<Transaction>>
    getTransactionsByReceiverIBAN(@PathVariable("iban") String iban) {
        List<Transaction> list =
                transactionService.getTransactionsByReceiverIBAN(iban);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * Retrieves transactions by amount range.
     *
     * @param from the minimum amount value (inclusive)
     * @param to   the maximum amount value (inclusive)
     * @return a list of transactions within the specified amount range
     */
    @GetMapping(value = "/get/by/amount/between/{from}/{to}")
    public ResponseEntity<List<Transaction>>
    getTransactionsByAmountBetween(@PathVariable("from") BigDecimal from,
                                   @PathVariable("to") BigDecimal to) {
        List<Transaction> list =
                transactionService.getTransactionsByAmountBetween(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * Retrieves transactions by date range.
     *
     * @param from the start date and time (inclusive)
     * @param to   the end date and time (inclusive)
     * @return a list of transactions within the specified date range
     */
    @GetMapping(value = "/get/by/date/between/{from}/{to}")
    public ResponseEntity<List<Transaction>>
    getTransactionsByDateBetween(@PathVariable("from") LocalDateTime from,
                                 @PathVariable("to") LocalDateTime to) {
        List<Transaction> list =
                transactionService.getTransactionsByCreatedBetween(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}