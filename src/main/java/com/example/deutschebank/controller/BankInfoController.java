package com.example.deutschebank.controller;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.service.interfaces.BankInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank-info")
public class BankInfoController {
    private final BankInfoService bankInfoService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BankInfo> createBankInfo(@RequestBody BankInfo bankInfo) {
        bankInfoService.createBankInfo(bankInfo);
        return ResponseEntity.status(HttpStatus.OK).body(bankInfo);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public BankInfo getBankInfo() {
        return bankInfoService.getBankInfo();
    }

    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BankInfo> updateBankInfo(@RequestBody BankInfo bankInfo) {
        bankInfoService.updateBankInfo(bankInfo);
        return ResponseEntity.status(HttpStatus.OK).body(bankInfo);
    }
}