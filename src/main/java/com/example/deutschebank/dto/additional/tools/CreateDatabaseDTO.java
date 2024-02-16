package com.example.deutschebank.dto.additional.tools;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDatabaseDTO {
    @NotNull(message = "Branch quantity should not be null!")
    @Min(value = 1, message = "Branch quantity should be minimum 1!")
    private int branchesQuantity;

    @NotNull(message = "Employee quantity should not be null!")
    @Min(value = 1, message = "Employee quantity should be minimum 1!")
    private int employeesQuantity;

    @NotNull(message = "Clients quantity should not be null!")
    @Min(value = 1, message = "Clients quantity should be minimum 1!")
    private int clientsQuantity;

    @NotNull(message = "Credits quantity should not be null!")
    @Min(value = 0, message = "Credits quantity should be 0 or more!")
    private int creditsQuantity;
    @NotNull(message = "Transactions quantity should not be null!")
    @Min(value = 0, message = "Transactions quantity should be 0 or more!")
    private int transactionsQuantity;
}