package com.example.deutschebank.service.interfaces.additionaltools;

import org.springframework.http.ResponseEntity;

public interface ToolsService {
    void generateBankInfoToDB();
    void deleteBankInfoFromDB();
    void generateTransactionsToDB(int quantity);
    void deleteAllTransactionsFromDB();
    ResponseEntity<byte[]> getDBSchema();
    ResponseEntity<byte[]> getDbRawSchema();
    ResponseEntity<byte[]> getLogo();
    ResponseEntity<byte[]> getRawLogo();
}
