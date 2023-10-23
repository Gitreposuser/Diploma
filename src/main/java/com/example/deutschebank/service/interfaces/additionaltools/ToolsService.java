package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.dto.additional.tools.CreateDatabaseDTO;
import org.springframework.http.ResponseEntity;

public interface ToolsService {
    void generateDataBase(CreateDatabaseDTO createDatabaseDTO);

    /*
    void generateBankBranchStructureToDB(Integer branchNumber,
                                         Location location);


    void generateBankBranchesStructureToDB(Integer quantity, Location location);
     */

    void deleteAllBankBranchesFromDB();

    // void generateBankInfoToDB();

    void deleteBankInfoFromDB();
    /*

    UUID generateClientStructureToDB(UUID managerId);

    List<UUID> generateClientsStructureToDB(int quantity,
                                            List<UUID> managersId);
*/
    void deleteAllClientsFromDB();
/*
    void generateCreditAccountToDB(UUID clientId);

    void generateCreditAccountsToDB(int quantity, List<UUID> clientsId);
*/

    void deleteAllCreditAccountsFromDB();

    //void generateDebitAccountToDB();

    void deleteAllDebitAccountsFromDB();

    //void generateEmployeeStructureToDB();

    //void generateEmployeesStructureToDB(Integer quantity);

    void deleteAllEmployeesFromDB();

    //void generateLocationToDB();

    //void generateLocationsToDB(Integer quantity);

    void deleteAllLocationsFromDB();

    //void generatePersonalDetailToDB();

    //void generatePersonalDetailsToDB(Integer quantity);

    void deleteAllPersonalDetailsFromDB();


/*
    void generateTransactionsToDB(int quantity, List<UUID> clientsId);

     */
    void deleteAllTransactionsFromDB();


    //void generateWorkDetailToDB();

    //void generateWorkDetailsToDB(Integer quantity);

    void deleteAllWorkDetailsFromDB();

    ResponseEntity<byte[]> getDBSchema();

    ResponseEntity<byte[]> getDbRawSchema();

    ResponseEntity<byte[]> getLogo();

    ResponseEntity<byte[]> getRawLogo();

    void runTest();
}