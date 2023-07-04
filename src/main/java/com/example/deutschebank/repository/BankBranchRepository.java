package com.example.deutschebank.repository;

import com.example.deutschebank.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankBranchRepository extends JpaRepository<BankInfo, UUID> {
}
