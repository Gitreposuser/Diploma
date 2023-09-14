package com.example.deutschebank.repository;

import com.example.deutschebank.entity.DebitAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DebitAccountRepository extends JpaRepository<DebitAccount, UUID> {
}