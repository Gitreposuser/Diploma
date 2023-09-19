package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.entity.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ToolsService {
    void generateBankBranchStructureToDB(int branchNumber);

    void generateBankBranchesStructureToDB(int quantity,
                                           int startBranchNumber);

    void deleteAllBankBranchesFromDB();

    void generateBankInfoToDB();

    void deleteBankInfoFromDB();

    void deleteAllClientsFromDB();

    void deleteAllCreditAccountsFromDB();

    void deleteAllDebitAccountsFromDB();

    void deleteAllEmployeesFromDB();

    void generateLocationToDB();

    void generateLocationsToDB(int quantity);

    void deleteAllLocationsFromDB();

    void generatePersonalDetailToDB();

    void generatePersonalDetailsToDB(int quantity);

    void deleteAllPersonalDetailsFromDB();

    void generateTransactionToDB();

    void generateTransactionsToDB(int quantity);

    void deleteAllTransactionsFromDB();

    void generateWorkDetailToDB();

    void generateWorkDetailsToDB(int quantity);

    void deleteAllWorkDetailsFromDB();

    ResponseEntity<byte[]> getDBSchema();

    ResponseEntity<byte[]> getDbRawSchema();

    ResponseEntity<byte[]> getLogo();

    ResponseEntity<byte[]> getRawLogo();

    ResponseEntity<String> runTest();
}
