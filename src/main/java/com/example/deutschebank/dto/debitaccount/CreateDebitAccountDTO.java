package com.example.deutschebank.dto.debitaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateDebitAccountDTO {
    private String iban;
    private String debitStatus;
    private BigDecimal balance;
    private BigDecimal depositInterest;
    private BigDecimal creditLine;
    private LocalDateTime startFrom;
}
