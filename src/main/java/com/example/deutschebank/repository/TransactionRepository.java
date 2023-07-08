package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,
        UUID> {
    @Query("SELECT tr " +
            "FROM Transaction tr " +
            "WHERE tr.emitterIban = :iban ")
    List<Transaction> getTransactionsByEmitterIBAN(String iban);

    @Query("SELECT tr " +
            "FROM Transaction tr " +
            "WHERE tr.receiverIban = :iban ")
    List<Transaction> getTransactionsByReceiverIBAN(String iban);

    @Query("SELECT tr " +
            "FROM Transaction tr " +
            "WHERE tr.amount > :from AND tr.amount < :to ")
    List<Transaction> getTransactionsByAmountInRange(int from,
                                                     int to);

    @Query("SELECT tr " +
            "FROM Transaction tr " +
            "WHERE tr.created > :from AND tr.created < :to ")
    List<Transaction> getTransactionsByDateInRange(LocalDateTime from,
                                                   LocalDateTime to);
}
