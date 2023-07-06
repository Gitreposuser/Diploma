package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {
    void createTransaction(Transaction transaction);

    Optional<List<Transaction>> getAllTransactions();

    Optional<Transaction> getTransactionById(UUID id);

    Optional<List<Transaction>> getTransactionsByEmitterIBAN(String iban);

    Optional<List<Transaction>> getTransactionsByReceiverIBAN(String iban);

    Optional<List<Transaction>> getTransactionsByAmountInRange(int from,
                                                               int to);

    Optional<List<Transaction>> getTransactionsByDate(LocalDate date);

    void generateTransactions(int quantity);
}
