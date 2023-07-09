package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    /**
     * Declares interface for creating transaction
     *
     * @param transaction provide emitter, receiver IBAN and amount
     *                    of transaction
     */
    void createTransaction(Transaction transaction);

    /**
     * Declares interface for get all existent transactions
     *
     * @return
     */
    List<Transaction> getAllTransactions();

    /**
     * Declares interface for get all transactions by emitter IBAN
     *
     * @param iban emitters IBAN for search
     * @return all transactions correspond emitter IBAN
     */
    List<Transaction> getTransactionsByEmitterIBAN(String iban);

    /**
     * Declares interface for get all transactions by receiver IBAN
     *
     * @param iban receivers IBAN for search
     * @return all transactions correspond receiver IBAN
     */
    List<Transaction> getTransactionsByReceiverIBAN(String iban);

    /**
     * Declares interface for get all transactions in range from - to,
     * by amount
     *
     * @param from starting amount for search
     * @param to ending amount for search
     * @return all transactions by amount in range
     */
    List<Transaction> getTransactionsByAmountBetween(BigDecimal from,
                                                     BigDecimal to);

    /**
     * Declares interface for get all transactions in range from - to,
     * by date
     * @param from starting date and time
     * @param to ending date and time
     * @return all transactions by date and time
     */
    List<Transaction> getTransactionsByCreatedBetween(LocalDateTime from,
                                                      LocalDateTime to);

    /**
     * Declares interface for generating random transactions
     * @param quantity quantity of generates transactions
     */
    void generateTransactions(int quantity);
}