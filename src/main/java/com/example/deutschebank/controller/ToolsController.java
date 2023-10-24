package com.example.deutschebank.controller;

import com.example.deutschebank.dto.additional.tools.CreateDatabaseDTO;
import com.example.deutschebank.service.interfaces.additionaltools.ToolsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tools")
public class ToolsController {
    private final ToolsService toolsService;

    @PostMapping("/generate-db")
    public void generateDataBase(@RequestBody CreateDatabaseDTO createDatabaseDTO) {
        toolsService.generateDataBase(createDatabaseDTO);
    }

    @DeleteMapping("/bank-branch/delete/all")
    public void deleteAllBankBranchesFromDB() {
        toolsService.deleteAllBankBranchesFromDB();
    }

    @DeleteMapping("/bank-info/delete")
    public void deleteBankInfoFromDB() {
        toolsService.deleteBankInfoFromDB();
    }

    @DeleteMapping("/client/delete/all")
    public void deleteAllClientsFromDB() {
        toolsService.deleteAllClientsFromDB();
    }

    @DeleteMapping("/credit-account/delete/all")
    public void deleteAllCreditAccountsFromDB() {
        toolsService.deleteAllCreditAccountsFromDB();
    }

    @DeleteMapping("/debit-account/delete/all")
    public void deleteAllDebitAccountsFromDB() {
        toolsService.deleteAllDebitAccountsFromDB();
    }

    @DeleteMapping("/employee/delete/all")
    public void deleteAllEmployeesFromDB() {
        toolsService.deleteAllEmployeesFromDB();
    }

    @DeleteMapping("/location/delete/all")
    public void deleteAllLocationsFromDB() {
        toolsService.deleteAllLocationsFromDB();
    }

    @DeleteMapping("/personal-detail/delete/all")
    public void deleteAllPersonalDetailsFromDB() {
        toolsService.deleteAllPersonalDetailsFromDB();
    }

    @DeleteMapping("/transaction/delete/all")
    public void deleteAllTransactionsFromDB() {
        toolsService.deleteAllTransactionsFromDB();
    }

    @DeleteMapping("/work-detail/delete/all")
    public void deleteAllWorkDetailsFromDB() {
        toolsService.deleteAllWorkDetailsFromDB();
    }

    @DeleteMapping("/database/delete/all")
    public void deleteAllFromDB() {
        toolsService.deleteAllCreditAccountsFromDB();
        toolsService.deleteAllClientsFromDB();
        toolsService.deleteAllEmployeesFromDB();
        toolsService.deleteAllBankBranchesFromDB();
        toolsService.deleteAllDebitAccountsFromDB();
        toolsService.deleteAllLocationsFromDB();
        toolsService.deleteAllPersonalDetailsFromDB();
        toolsService.deleteAllTransactionsFromDB();
        toolsService.deleteAllWorkDetailsFromDB();
        toolsService.deleteBankInfoFromDB();
    }

    @GetMapping("/db-schema/get")
    public ResponseEntity<byte[]> getDbSchemaFromDB() {
        return toolsService.getDBSchema();
    }

    @GetMapping("/raw-schema/get")
    public ResponseEntity<byte[]> getDbRawSchemaFromDB() {
        return toolsService.getDbRawSchema();
    }

    @GetMapping("/logo/get")
    public ResponseEntity<byte[]> getLogoFromDB() {
        return toolsService.getLogo();
    }

    @GetMapping("/raw-logo/get")
    public ResponseEntity<byte[]> getRawLogoFromDB() {
        return toolsService.getRawLogo();
    }

    @GetMapping("/test")
    public void runTest() {
        toolsService.runTest();
    }
}