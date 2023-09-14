package com.example.deutschebank.controller;

import com.example.deutschebank.model.transaction.CreateTransactionDTO;
import com.example.deutschebank.model.transaction.GetTransactionDTO;
import com.example.deutschebank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping(value = "/create")
    public void create(@RequestBody CreateTransactionDTO createDTO) {
        transactionService.createTransaction(createDTO);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<GetTransactionDTO>> getAllTransactions() {
        List<GetTransactionDTO> getAllDTOs =
                transactionService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @GetMapping(value = "/get/emitter/by-iban/{iban}")
    public ResponseEntity<List<GetTransactionDTO>>
    getTransactionsByEmitterIBAN(@PathVariable String iban) {
        List<GetTransactionDTO> allByEmitter =
                transactionService.getTransactionsByEmitterIBAN(iban);
        return ResponseEntity.status(HttpStatus.OK).body(allByEmitter);
    }

    @GetMapping(value = "/get/receiver/by-iban/{iban}")
    public ResponseEntity<List<GetTransactionDTO>>
    getTransactionsByReceiverIBAN(@PathVariable String iban) {
        List<GetTransactionDTO> allByReceiver =
                transactionService.getTransactionsByReceiverIBAN(iban);
        return ResponseEntity.status(HttpStatus.OK).body(allByReceiver);
    }

    @GetMapping(value = "/get/by/amount/between/{from}/{to}")
    public ResponseEntity<List<GetTransactionDTO>>
    getTransactionsByAmountBetween(@PathVariable BigDecimal from,
                                   @PathVariable BigDecimal to) {
        List<GetTransactionDTO> allAmountBetween =
                transactionService.getTransactionsByAmountBetween(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(allAmountBetween);
    }

    @GetMapping(value = "/get/by/date/between/{from}/{to}")
    public ResponseEntity<List<GetTransactionDTO>>
    getTransactionsByDateBetween(@PathVariable LocalDateTime from,
                                 @PathVariable LocalDateTime to) {
        List<GetTransactionDTO> allDateBetween =
                transactionService.getTransactionsByCreatedBetween(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(allDateBetween);
    }
}