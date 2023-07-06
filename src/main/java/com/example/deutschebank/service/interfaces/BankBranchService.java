package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.BankBranch;

public interface BankBranchService {
    void createBankBranch(BankBranch bankBranch);

    BankBranch getBankBranch();

    BankBranch updateBankBranch(BankBranch bankBranch);

    void deleteBankBranch(BankBranch bankBranch);
}