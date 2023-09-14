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

    @GetMapping("/bank-info/generate")
    public void generateBankInfoToDB() {
        toolsService.generateBankInfoToDB();
    }

    @DeleteMapping("/bank-info/delete")
    public void deleteBankInfoFromDB() {
        toolsService.deleteBankInfoFromDB();
    }

    @GetMapping("/transactions/generate/{quantity}")
    public void generateTransactionsToDB(@PathVariable Integer quantity) {
        toolsService.generateTransactionsToDB(quantity);
    }

    @DeleteMapping("/transactions/delete/all")
    public void deleteAllTransactionsFromDB() {
        toolsService.deleteAllTransactionsFromDB();
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
}