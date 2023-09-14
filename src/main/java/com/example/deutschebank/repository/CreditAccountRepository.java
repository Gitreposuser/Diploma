package com.example.deutschebank.repository;

import com.example.deutschebank.entity.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount,
        UUID> {
}
