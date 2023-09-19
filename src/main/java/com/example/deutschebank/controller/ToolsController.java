package com.example.deutschebank.controller;

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

    @GetMapping("/generate-db")
    public void generateDataBase() {
        // Code something already!!!
    }

    @GetMapping("/bank-branch-structure/generate/by-branch-number/{branchNumber}")
    public void generateBankBranchStructureToDB(@PathVariable int branchNumber) {
        toolsService.generateBankBranchStructureToDB(branchNumber);
    }

    @GetMapping("/bank-branch-structure/generate/quantity/{quantity}/by" +
            "-starting-branch-number/{startingBranchNumber}")
    public void generateBankBranchesStructureToDB(@PathVariable int quantity,
                                                  @PathVariable int startingBranchNumber) {
        toolsService.generateBankBranchesStructureToDB(quantity, startingBranchNumber);
    }

    @DeleteMapping("/bank-branch/delete/all")
    public void deleteAllBankBranchesFromDB() {
        toolsService.deleteAllBankBranchesFromDB();
    }

    @GetMapping("/bank-info/generate")
    public void generateBankInfoToDB() {
        toolsService.generateBankInfoToDB();
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

    @GetMapping("/location/generate")
    public void generateLocationToDB() {
        toolsService.generateLocationToDB();
    }

    @GetMapping("/location/generate/quantity/{quantity}")
    public void generateLocationsToDB(@PathVariable int quantity) {
        toolsService.generateLocationsToDB(quantity);
    }

    @DeleteMapping("/location/delete/all")
    public void deleteAllLocationsFromDB() {
        toolsService.deleteAllLocationsFromDB();
    }

    @GetMapping("/personal-detail/generate")
    public void generatePersonalDetailToDB() {
        toolsService.generatePersonalDetailToDB();
    }

    @GetMapping("/personal-detail/generate/quantity/{quantity}")
    public void generatePersonalDetailsToDB(@PathVariable int quantity) {
        toolsService.generatePersonalDetailsToDB(quantity);
    }

    @DeleteMapping("/personal-detail/delete/all")
    public void deleteAllPersonalDetailsFromDB() {
        toolsService.deleteAllPersonalDetailsFromDB();
    }

    @GetMapping("/transaction/generate")
    public void generateTransactionsToDB() {
        toolsService.generateTransactionToDB();
    }

    @GetMapping("/transaction/generate/{quantity}")
    public void generateTransactionsToDB(@PathVariable int quantity) {
        toolsService.generateTransactionsToDB(quantity);
    }

    @DeleteMapping("/transaction/delete/all")
    public void deleteAllTransactionsFromDB() {
        toolsService.deleteAllTransactionsFromDB();
    }

    @GetMapping("/work-detail/generate")
    public void generateWorkDetail() {
        toolsService.generateWorkDetailToDB();
    }

    @GetMapping("/work-detail/generate/{quantity}")
    public void generateWorkDetailsToDB(@PathVariable int quantity) {
        toolsService.generateWorkDetailsToDB(quantity);
    }

    @DeleteMapping("/work-detail/delete/all")
    public void deleteAllWorkDetailsFromDB() {
        toolsService.deleteAllWorkDetailsFromDB();
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
    public ResponseEntity<String> runTest() {
        return toolsService.runTest();
    }
}