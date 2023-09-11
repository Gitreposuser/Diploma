package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.model.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.model.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.model.bankbranch.UpdateBankBranchDTO;

import java.util.List;
import java.util.UUID;

public interface BankBranchService {
    CreateBankBranchDTO createBankBranch(CreateBankBranchDTO createDTO);

    GetBankBranchDTO getBankBranch(UUID uuid);

    List<GetBankBranchDTO> getAllBankBranches();

    void updateBankBranch(UpdateBankBranchDTO updateDTO);

    void deleteBankBranch(UUID uuid);
}