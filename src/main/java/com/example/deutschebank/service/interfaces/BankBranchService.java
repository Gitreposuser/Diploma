package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.UpdateBankBranchDTO;

import java.util.List;
import java.util.UUID;

public interface BankBranchService {
    void createBankBranch(CreateBankBranchDTO createDTO);

    GetBankBranchDTO getBankBranchById(UUID uuid);

    List<GetBankBranchDTO> getAllBankBranches();

    void updateBankBranchById(UpdateBankBranchDTO updateDTO);

    void deleteBankBranchById(UUID uuid);
}