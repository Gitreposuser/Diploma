package com.example.deutschebank.controller;

import com.example.deutschebank.dto.bankaccount.CreateBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.GetBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.UpdateBankAccountDTO;
import com.example.deutschebank.service.interfaces.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank-info")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @PostMapping(value = "/create")
    public void createBankInfo(@RequestBody @Valid CreateBankAccountDTO createDTO) {
        bankAccountService.createBankInfo(createDTO);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<GetBankAccountDTO> getBankInfo() {
        return ResponseEntity.ok().body(bankAccountService.getBankInfo());
    }

    @PutMapping(value = "/update")
    public void updateBankInfo(@RequestBody @Valid UpdateBankAccountDTO updateDTO) {
        bankAccountService.updateBankInfo(updateDTO);
    }
}