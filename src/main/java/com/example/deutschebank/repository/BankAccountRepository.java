package com.example.deutschebank.repository;

import com.example.deutschebank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    @Query("SELECT bi.balance FROM BankAccount bi " +
            "WHERE bi.id = 1 ")
    BigDecimal getBalance();

    @Modifying
    @Query("UPDATE BankAccount bi " +
            "SET bi.balance = :balance " +
            "WHERE bi.id = 1 ")
    void setBalance(BigDecimal balance);
}