package com.example.deutschebank.dto.debitaccount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateDebitAccountDTO {
    @NotNull(message = "Iban should not be null!")
    @NotBlank(message = "Iban should not be empty!")
    private String iban;

    @NotNull(message = "Debit status should not be null!")
    @NotBlank(message = "Debit status should not be empty!")
    private String debitStatus;

    @NotNull(message = "Balance should not be null!")
    @Min(value = 0, message = "Balance should start from 0!")
    private BigDecimal balance;

    @NotNull(message = "Deposit interest should not be null!")
    @Min(value = 0, message = "Deposit interest should start from 0!")
    private BigDecimal depositInterest;

    @NotNull(message = "Credit line should not be null!")
    @Min(value = 0, message = "Credit line should start from 0!")
    private BigDecimal creditLine;
}
