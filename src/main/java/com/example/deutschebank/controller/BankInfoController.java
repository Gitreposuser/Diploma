package com.example.deutschebank.controller;

import com.example.deutschebank.dto.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.UpdateBankInfoDTO;
import com.example.deutschebank.service.interfaces.BankInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank-info")
public class BankInfoController {
    private final BankInfoService bankInfoService;

    @PostMapping(value = "/create")
    public void createBankInfo(@RequestBody CreateBankInfoDTO createDTO) {
        bankInfoService.createBankInfo(createDTO);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<GetBankInfoDTO> getBankInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(bankInfoService.getBankInfo());
    }

    @PutMapping(value = "/update")
    public void updateBankInfo(@RequestBody UpdateBankInfoDTO updateDTO) {
        bankInfoService.updateBankInfo(updateDTO);
    }
}