package com.example.deutschebank.controller;

import com.example.deutschebank.dto.debitaccount.*;
import com.example.deutschebank.entity.enums.DebitStatus;
import com.example.deutschebank.service.interfaces.DebitAccountService;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/debit-account")
public class DebitAccountController {
    private final DebitAccountService debitAccountService;

    @PostMapping(value = "/create")
    public void createDebitAccount(@RequestBody @Valid CreateDebitAccountDTO createDTO) {
        debitAccountService.createDebitAccount(createDTO);
    }

    @GetMapping(value = "/get/by/id/{uuid}")
    public ResponseEntity<GetDebitAccountDTO> getDebitAccountById(@PathVariable UUID uuid) {
        GetDebitAccountDTO getDTO =
                debitAccountService.getDebitAccountById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all/by/debit-status/{debit_status}")
    public ResponseEntity<List<GetDebitAccountDTO>> getAllDebitAccountsWithStatus
            (@PathVariable DebitStatus debit_status) {
        List<GetDebitAccountDTO> getAllDTOs =
                debitAccountService.getAllDebitAccountsByDebitStatus(debit_status);
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetDebitAccountDTO>> getAllDebitAccounts() {
        List<GetDebitAccountDTO> getAllDTOs =
                debitAccountService.getAllDebitAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by/id")
    public void updateDebitAccountById(@RequestBody @Valid UpdateDebitAccountDTO updateDTO) {
        debitAccountService.updateDebitAccountById(updateDTO);
    }

    @PutMapping(value = "/transfer-funds/by/iban")
    public void transferFundsByIban(@RequestBody @Valid TransferFundsDTO transferDTO) {
        debitAccountService.transferFundsByIban(transferDTO);
    }

    @PutMapping(value = "/pay-debt")
    public void payDebt(@RequestBody @Valid PayDeptDTO payDeptDTO) {
        debitAccountService.payDebt(payDeptDTO);
    }
}