package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,
        UUID> {
}
