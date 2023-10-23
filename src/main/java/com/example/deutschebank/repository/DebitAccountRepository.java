package com.example.deutschebank.repository;

import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.enums.DebitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DebitAccountRepository extends JpaRepository<DebitAccount, UUID> {
    List<DebitAccount> getAllDebitAccountsByDebitStatus(DebitStatus status);
}