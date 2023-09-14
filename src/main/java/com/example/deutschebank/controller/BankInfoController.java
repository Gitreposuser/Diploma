package com.example.deutschebank.controller;

import com.example.deutschebank.model.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.model.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.model.bankinfo.UpdateBankInfoDTO;
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
    public void createBankInfo(@RequestBody CreateBankInfoDTO createDTO) {
        bankInfoService.createBankInfo(createDTO);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<GetBankInfoDTO> getBankInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(bankInfoService.getBankInfo());
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UpdateBankInfoDTO> updateBankInfo(@RequestBody UpdateBankInfoDTO updateDTO) {
        bankInfoService.updateBankInfo(updateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }
}