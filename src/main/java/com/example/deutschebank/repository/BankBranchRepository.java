package com.example.deutschebank.repository;

import com.example.deutschebank.entity.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, UUID> {
    boolean existsByBranchNumber(Integer branchNumber);

    @Query("SELECT bb FROM BankBranch bb " +
            "WHERE bb.branchNumber = :branchNumber ")
    BankBranch getByBranchNumber(Integer branchNumber);

    @Query("SELECT bb FROM BankBranch bb " +
            "WHERE bb.active = true ")
    List<BankBranch> getAllActiveBankBranches();
}