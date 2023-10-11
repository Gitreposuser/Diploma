package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.dto.bankbranch.GetBranchCityDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ToolsService {
    //
    // Debug
    //
    GetBranchCityDTO getBranchCity(UUID uuid);

    UUID generateBankBranchStructureToDB(int branchNumber);

    List<UUID> generateBankBranchesStructureToDB(int quantity);

    void deleteAllBankBranchesFromDB();

    void generateBankInfoToDB();

    void deleteBankInfoFromDB();

    UUID generateClientStructureToDB(UUID managerId);

    List<UUID> generateClientsStructureToDB(int quantity,
                                            List<UUID> managersId);

    void deleteAllClientsFromDB();

    void generateCreditAccountToDB(UUID clientId);

    void generateCreditAccountsToDB(int quantity, List<UUID> clientsId);

    void deleteAllCreditAccountsFromDB();

    UUID generateDebitAccountToDB();

    void deleteAllDebitAccountsFromDB();

    UUID generateEmployeeStructureToDB(UUID branchId);

    List<UUID> generateEmployeesStructureToDB(int quantity,
                                              List<UUID> branchesId);

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

    void runTest();
}