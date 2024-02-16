package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchInfoDTO;
import com.example.deutschebank.dto.bankbranch.UpdateBankBranchDTO;

import java.util.List;
import java.util.UUID;

public interface BankBranchService {
    String createBankBranch(CreateBankBranchDTO createDTO);

    GetBankBranchDTO getBankBranchById(UUID uuid);

    GetBankBranchDTO getBankBranchByNumber(Integer branchNumber);

    GetBankBranchInfoDTO getBankBranchInfoByNumber(Integer branchNumber);

    List<GetBankBranchDTO> getAllActiveBankBranches();

    List<GetBankBranchDTO> getAllBankBranches();

    void updateBankBranchById(UpdateBankBranchDTO updateDTO);

    void markBankBranchAsDeletedById(UUID uuid);

    void deleteBankBranchById(UUID uuid);

    void deleteAllBankBranch();
}