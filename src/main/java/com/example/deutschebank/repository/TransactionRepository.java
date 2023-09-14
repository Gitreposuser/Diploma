package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,
        UUID> {

    List<Transaction> getTransactionsByEmitterIban(String iban);

    List<Transaction> getTransactionsByReceiverIban(String iban);

    List<Transaction> getTransactionsByAmountBetween(BigDecimal from,
                                                     BigDecimal to);

    List<Transaction> getTransactionsByCreatedBetween(LocalDateTime from,
                                                      LocalDateTime to);
}
