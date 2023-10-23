package com.example.deutschebank.controller;

import com.example.deutschebank.dto.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.GetCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.UpdateCreditAccountDTO;
import com.example.deutschebank.service.interfaces.CreditAccountService;
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
@RequestMapping(value = "/credit-account")
public class CreditAccountController {
    private final CreditAccountService creditAccountService;

    @PostMapping(value = "/create")
    public void createCreditAccount(@RequestBody CreateCreditAccountDTO createDTO) {
        creditAccountService.createCreditAccount(createDTO);
    }

    @GetMapping(value = "/get/by/id/{uuid}")
    public ResponseEntity<GetCreditAccountDTO> getCreditAccountById(@PathVariable UUID uuid) {
        GetCreditAccountDTO getDTO = creditAccountService.getCreditAccountById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetCreditAccountDTO>> getAllCreditDetails() {
        List<GetCreditAccountDTO> getAllDTOs =
                creditAccountService.getAllCreditAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by/id")
    public void updateCreditAccountById(@RequestBody UpdateCreditAccountDTO updateDTO) {
        creditAccountService.updateCreditAccountById(updateDTO);
    }

    @DeleteMapping(value = "/delete/by-id/{uuid}")
    public void deleteWorkDetailById(@PathVariable UUID uuid) {
        creditAccountService.deleteCreditAccountById(uuid);
    }
}
