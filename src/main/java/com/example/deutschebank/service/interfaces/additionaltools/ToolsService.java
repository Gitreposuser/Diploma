package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.dto.additional.tools.CreateDatabaseDTO;
import org.springframework.http.ResponseEntity;

public interface ToolsService {
    void generateDataBase(CreateDatabaseDTO createDatabaseDTO);

    void deleteAllBankBranchesFromDB();

    void deleteBankInfoFromDB();

    void deleteAllClientsFromDB();

    void deleteAllCreditAccountsFromDB();

    void deleteAllDebitAccountsFromDB();

    void deleteAllEmployeesFromDB();

    void deleteAllLocationsFromDB();

    void deleteAllPersonalDetailsFromDB();

    void deleteAllTransactionsFromDB();

    void deleteAllWorkDetailsFromDB();

    ResponseEntity<byte[]> getDBSchema();

    ResponseEntity<byte[]> getDbRawSchema();

    ResponseEntity<byte[]> getLogo();

    ResponseEntity<byte[]> getRawLogo();

    ResponseEntity<String> runTest();
}