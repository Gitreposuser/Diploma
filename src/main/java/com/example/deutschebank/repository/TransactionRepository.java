package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,
        UUID> {

    /**
     * Declaring interface extension of JpaRepository
     * which get all transactions by emitter IBAN
     *
     * @param iban emitter IBAN for search
     * @return all transactions by emitter IBAN
     */
    List<Transaction> getTransactionsByEmitterIban(String iban);

    /**
     * Declaring interface extension of JpaRepository
     * which get all transactions by receiver IBAN
     *
     * @param iban receiver IBAN for search
     * @return all transactions by receiver IBAN
     */
    List<Transaction> getTransactionsByReceiverIban(String iban);

    /**
     * Declaring interface extension of JpaRepository
     * which get all transactions by amount in range from - to
     *
     * @param from starting amount
     * @param to   ending amount
     * @return all transactions by amount specified range
     */
    List<Transaction> getTransactionsByAmountBetween(BigDecimal from,
                                                     BigDecimal to);

    /**
     * Declaring interface extension of JpaRepository
     * which get all transactions by date in range from - to
     *
     * @param from starting date
     * @param to   ending date
     * @return all transactions by date in specified range
     */
    List<Transaction> getTransactionsByCreatedBetween(LocalDateTime from,
                                                      LocalDateTime to);
}
