package com.example.deutschebank.controller;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.repository.BankBranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankBranchController {
    private final BankBranchRepository bankBranchRepository;

    public void createBankBranch(BankInfo bankInfo) {
        bankBranchRepository.save(bankInfo);
    }
}