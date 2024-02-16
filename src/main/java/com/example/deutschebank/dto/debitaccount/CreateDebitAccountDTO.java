package com.example.deutschebank.dto.debitaccount;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateDebitAccountDTO {
    private String iban;
    private String debitStatus;
    private BigDecimal balance;
    private BigDecimal depositInterest;
    private BigDecimal creditLine;
}
