package com.example.deutschebank.controller;

import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchInfoDTO;
import com.example.deutschebank.dto.bankbranch.UpdateBankBranchDTO;
import com.example.deutschebank.service.interfaces.BankBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank-branch")
public class BankBranchController {
    private final BankBranchService bankBranchService;

    @PostMapping(value = "/create")
    public void createBankBranch(@RequestBody CreateBankBranchDTO createDTO) {
        bankBranchService.createBankBranch(createDTO);
    }

    @GetMapping(value = "/get/by/id/{uuid}")
    public ResponseEntity<GetBankBranchDTO> getBankBranchById(@PathVariable UUID uuid) {
        GetBankBranchDTO getDTO = bankBranchService.getBankBranchById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/by/branch-number/{branch_number}")
    public ResponseEntity<GetBankBranchDTO> getBankBranchByBranchNumber
            (@PathVariable Integer branch_number) {
        GetBankBranchDTO getDTO =
                bankBranchService.getBankBranchByNumber(branch_number);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/info/by/branch-number/{branch_number}")
    public ResponseEntity<GetBankBranchInfoDTO> getBankBranchInfoByBranchNumber
            (@PathVariable Integer branch_number) {
        GetBankBranchInfoDTO getDTO =
                bankBranchService.getBankBranchInfoByNumber(branch_number);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all/active")
    public ResponseEntity<List<GetBankBranchDTO>> getAllActiveBankBranches() {
        List<GetBankBranchDTO> getAllDTOs =
                bankBranchService.getAllActiveBankBranches();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetBankBranchDTO>> getAllBankBranches() {
        List<GetBankBranchDTO> getAllDTOs =
                bankBranchService.getAllBankBranches();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by/id")
    public void updateBankBranchById(@RequestBody UpdateBankBranchDTO updateDTO) {
        bankBranchService.updateBankBranchById(updateDTO);
    }

    @DeleteMapping(value = "/delete/by/id/{uuid}")
    public void markBankBranchAsDeletedById(@PathVariable UUID uuid) {
        bankBranchService.markBankBranchAsDeletedById(uuid);
    }
}