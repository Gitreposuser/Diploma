package com.example.deutschebank.controller;

import com.example.deutschebank.dto.bankbranch.GetBranchCityDTO;
import com.example.deutschebank.service.interfaces.additionaltools.ToolsService;
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
@RequestMapping("/tools")
public class ToolsController {
    private final ToolsService toolsService;

    @GetMapping("/generate-db/branch-quantity/{branches_quantity}/employee" +
            "-quantity/{employees_quantity}/client-quantity/{clients_quantity" +
            "}/credit-quantity/{credits_quantity}")
    public void generateDataBase(@PathVariable int branches_quantity,
                                 @PathVariable int employees_quantity,
                                 @PathVariable int clients_quantity,
                                 @PathVariable int credits_quantity) {
        toolsService.generateBankInfoToDB();
        List<UUID> branchesId =
                toolsService.generateBankBranchesStructureToDB(branches_quantity);
        List<UUID> employeesId = toolsService
                .generateEmployeesStructureToDB(employees_quantity, branchesId);
        List<UUID> clientsId =
                toolsService.generateClientsStructureToDB(clients_quantity,
                        employeesId);
        toolsService.generateCreditAccountsToDB(credits_quantity, clientsId);
    }

    //
    // Debug
    //
    @GetMapping("/bank-branch/get/city/by-id/{uuid}")
    public ResponseEntity<GetBranchCityDTO> getBranchCityFromDB(@PathVariable UUID uuid) {
        GetBranchCityDTO getBranchCityDTO =
                toolsService.getBranchCity(uuid);
        log.info(getBranchCityDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(getBranchCityDTO);
    }

    @GetMapping("/bank-branch-structure/generate/by-branch-number/{branchNumber}")
    public void generateBankBranchStructureToDB(@PathVariable int branchNumber) {
        toolsService.generateBankBranchStructureToDB(branchNumber);
    }

    @GetMapping("/bank-branch-structure/generate/quantity/{quantity}")
    public void generateBankBranchesStructureToDB(@PathVariable int quantity) {
        toolsService.generateBankBranchesStructureToDB(quantity);
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

    @GetMapping("/client/generate-structure/with/manager-id/{uuid}")
    public void generateClientStructureToDB(@PathVariable UUID uuid) {
        toolsService.generateClientStructureToDB(uuid);
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

    @GetMapping("/employee/generate-structure/with/branch-id/{uuid}")
    public void generateEmployeeStructureToDB(@PathVariable UUID uuid) {
        toolsService.generateEmployeeStructureToDB(uuid);
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
    public void runTest() {
        toolsService.runTest();
    }
}