package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.repository.BankBranchRepository;
import com.example.deutschebank.service.interfaces.BankBranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankBranchServiceImpl implements BankBranchService {
    private final BankBranchRepository bankBranchRepository;

    @Override
    public void createBankBranch(BankBranch bankBranch) {
        if (bankBranchRepository.findAll().isEmpty()) {
            bankBranchRepository.save(bankBranch);
        }
    }

    @Override
    public BankBranch getBankBranch() {
        return bankBranchRepository.findAll().get(0);
    }

    @Override
    public BankBranch updateBankBranch(BankBranch bankBranch) {
        bankBranchRepository.deleteAll();
        return bankBranchRepository.save(bankBranch);
    }

    @Override
    public void deleteBankBranch(BankBranch bankBranch) {
        bankBranchRepository.delete(bankBranch);
    }
}
