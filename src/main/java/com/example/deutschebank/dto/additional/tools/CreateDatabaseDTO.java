package com.example.deutschebank.dto.additional.tools;

import lombok.Data;

@Data
public class CreateDatabaseDTO {
    private int branchesQuantity;
    private int employeesQuantity;
    private int clientsQuantity;
    private int creditsQuantity;
    private int transactionsQuantity;
}
