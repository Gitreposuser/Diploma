package com.example.deutschebank.controller;

import com.example.deutschebank.dto.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.GetDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.UpdateDebitAccountDTO;
import com.example.deutschebank.entity.enums.DebitStatus;
import com.example.deutschebank.service.interfaces.DebitAccountService;
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
    public void createDebitAccount(@RequestBody CreateDebitAccountDTO createDTO) {
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
                debitAccountService.getAllDebitAccountByDebitStatus(debit_status);
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetDebitAccountDTO>> getAllDebitAccounts() {
        List<GetDebitAccountDTO> getAllDTOs =
                debitAccountService.getAllDebitAccount();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by/id")
    public void updateDebitAccountById(@RequestBody UpdateDebitAccountDTO updateDTO) {
        debitAccountService.updateDebitAccountById(updateDTO);
    }
}
