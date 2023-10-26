package com.example.deutschebank.repository;

import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.enums.DebitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface DebitAccountRepository extends JpaRepository<DebitAccount, UUID> {
    List<DebitAccount> getAllDebitAccountsByDebitStatus(DebitStatus status);

    boolean existsByIban(String iban);

    @Query("SELECT debitStatus FROM DebitAccount " +
            "WHERE iban = :iban")
    DebitStatus getDebitStatusByIban(String iban);

    @Query("SELECT id FROM DebitAccount " +
            "WHERE iban = :iban ")
    UUID getDebitAccountByIban(String iban);

    @Query("SELECT balance FROM DebitAccount " +
            "WHERE iban = :iban ")
    BigDecimal getBalanceByIban(String iban);

    @Modifying
    @Query("UPDATE DebitAccount " +
            "SET balance = :balance " +
            "WHERE id = :uuid ")
    void setBalance(UUID uuid, BigDecimal balance);
}