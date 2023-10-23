package com.example.deutschebank.controller;

import com.example.deutschebank.dto.additional.tools.CreateDatabaseDTO;
import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.entity.Client;
import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.service.interfaces.additionaltools.ToolsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
/*
    @GetMapping("/bank-info/generate")
    public void generateBankInfoToDB() {
        toolsService.generateBankInfoToDB();
    }

 */

    @DeleteMapping("/bank-info/delete")
    public void deleteBankInfoFromDB() {
        toolsService.deleteBankInfoFromDB();
    }
/*
    @GetMapping("/client/generate-structure/with/manager-id/{uuid}")
    public void generateClientStructureToDB(@PathVariable UUID uuid) {
        toolsService.generateClientStructureToDB(uuid);
    }
    */

    @DeleteMapping("/client/delete/all")
    public void deleteAllClientsFromDB() {
        toolsService.deleteAllClientsFromDB();
    }

    @DeleteMapping("/credit-account/delete/all")
    public void deleteAllCreditAccountsFromDB() {
        toolsService.deleteAllCreditAccountsFromDB();
    }
/*
    @GetMapping("/debit-account/generate")
    public void generateDebitAccountToDB() {
        toolsService.generateDebitAccountToDB();
    }


 */
    @DeleteMapping("/debit-account/delete/all")
    public void deleteAllDebitAccountsFromDB() {
        toolsService.deleteAllDebitAccountsFromDB();
    }
/*
    @GetMapping("/employee/generate-structure")
    public void generateEmployeeStructureToDB() {
        toolsService.generateEmployeeStructureToDB();
    }
 */

    @DeleteMapping("/employee/delete/all")
    public void deleteAllEmployeesFromDB() {
        toolsService.deleteAllEmployeesFromDB();
    }
/*
    @GetMapping("/location/generate")
    public void generateLocationToDB() {
        toolsService.generateLocationToDB();
    }

    @GetMapping("/location/generate/quantity/{quantity}")
    public void generateLocationsToDB(@PathVariable Integer quantity) {
        toolsService.generateLocationsToDB(quantity);
    }

 */

    @DeleteMapping("/location/delete/all")
    public void deleteAllLocationsFromDB() {
        toolsService.deleteAllLocationsFromDB();
    }

/*
    @GetMapping("/personal-detail/generate")
    public void generatePersonalDetailToDB() {
        toolsService.generatePersonalDetailToDB();
    }

    @GetMapping("/personal-detail/generate/quantity/{quantity}")
    public void generatePersonalDetailsToDB(@PathVariable Integer quantity) {
        toolsService.generatePersonalDetailsToDB(quantity);
    }

 */

    @DeleteMapping("/personal-detail/delete/all")
    public void deleteAllPersonalDetailsFromDB() {
        toolsService.deleteAllPersonalDetailsFromDB();
    }

    @DeleteMapping("/transaction/delete/all")
    public void deleteAllTransactionsFromDB() {
        toolsService.deleteAllTransactionsFromDB();
    }
/*
    @GetMapping("/work-detail/generate")
    public void generateWorkDetail() {
        toolsService.generateWorkDetailToDB();
    }

    @GetMapping("/work-detail/generate/{quantity}")
    public void generateWorkDetailsToDB(@PathVariable Integer quantity) {
        toolsService.generateWorkDetailsToDB(quantity);
    }

 */

    @DeleteMapping("/work-detail/delete/all")
    public void deleteAllWorkDetailsFromDB() {
        toolsService.deleteAllWorkDetailsFromDB();
    }

    @DeleteMapping("/database/delete/all")
    public void deleteAllFromDB() {
        toolsService.deleteAllBankBranchesFromDB();
        toolsService.deleteBankInfoFromDB();
        toolsService.deleteAllDebitAccountsFromDB();
        toolsService.deleteAllEmployeesFromDB();
        toolsService.deleteAllLocationsFromDB();
        toolsService.deleteAllPersonalDetailsFromDB();
        toolsService.deleteAllTransactionsFromDB();
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
    public void runTest() {
        toolsService.runTest();
    }
}