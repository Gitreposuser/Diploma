package com.example.deutschebank.repository;

import com.example.deutschebank.entity.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankBranchRepository extends JpaRepository<BankBranch, UUID> {
}