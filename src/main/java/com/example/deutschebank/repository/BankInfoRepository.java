package com.example.deutschebank.repository;

import com.example.deutschebank.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, UUID> {
    Optional<BankInfo> findById(UUID uuid);
}