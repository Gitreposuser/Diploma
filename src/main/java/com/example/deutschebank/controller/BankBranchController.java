package com.example.deutschebank.controller;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.model.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.model.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.model.bankbranch.UpdateBankBranchDTO;
import com.example.deutschebank.service.interfaces.BankBranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank-branch")
public class BankBranchController {
    private final BankBranchService bankBranchService;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateBankBranchDTO> createBankBranch(@RequestBody CreateBankBranchDTO createDTO) {
        bankBranchService.createBankBranch(createDTO);
        return ResponseEntity.status(HttpStatus.OK).body(createDTO);
    }

    @GetMapping(value = "/get/by-id/{uuid}")
    public ResponseEntity<GetBankBranchDTO> getBankBranch(@PathVariable UUID uuid) {
        GetBankBranchDTO getDTO = bankBranchService.getBankBranch(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetBankBranchDTO>> getAllBankBranch() {
        List<GetBankBranchDTO> getAllDTOs =
                bankBranchService.getAllBankBranches();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by-id")
    public void updateBankBranch(@RequestBody UpdateBankBranchDTO updateDTO) {
        bankBranchService.updateBankBranch(updateDTO);
    }

    @DeleteMapping(value = "/delete/by-id/{uuid}")
    public void deleteBankBranch(@PathVariable UUID uuid) {
        bankBranchService.deleteBankBranch(uuid);
    }
}