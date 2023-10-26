package com.example.deutschebank.repository;

import com.example.deutschebank.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Integer> {
    @Query("SELECT bi.balance FROM BankInfo bi " +
            "WHERE bi.id = 1 ")
    BigDecimal getBalance();

    @Modifying
    @Query("UPDATE BankInfo bi " +
            "SET bi.balance = :balance " +
            "WHERE bi.id = 1 ")
    void setBalance(BigDecimal balance);
}