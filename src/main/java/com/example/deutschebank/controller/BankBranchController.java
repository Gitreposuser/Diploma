package com.example.deutschebank.controller;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.service.interfaces.BankBranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank-branch")
public class BankBranchController {
    private final BankBranchService bankBranchService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void createBankBranch(BankBranch bankBranch) {
        bankBranchService.createBankBranch(bankBranch);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public BankBranch getBankBranch() {
        return bankBranchService.getBankBranch();
    }

    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BankBranch> updateBankInfo(@RequestBody BankBranch bankBranch) {
        bankBranchService.updateBankBranch(bankBranch);
        return ResponseEntity.status(HttpStatus.OK).body(bankBranch);
    }

    @PostMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BankBranch> deleteBankInfo(@RequestBody BankBranch bankBranch) {
        bankBranchService.updateBankBranch(bankBranch);
        return ResponseEntity.status(HttpStatus.OK).body(bankBranch);
    }
}