package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    void createTransaction(Transaction transaction);

    Optional<List<Transaction>> getAllTransactions();

    Optional<List<Transaction>> getTransactionsByEmitterIBAN(String iban);

    Optional<List<Transaction>> getTransactionsByReceiverIBAN(String iban);

    Optional<List<Transaction>> getTransactionsByAmountInRange(int from,
                                                               int to);

    Optional<List<Transaction>> getTransactionsByDateInRange(LocalDateTime from,
                                                             LocalDateTime to);

    void generateTransactions(int quantity);
}
