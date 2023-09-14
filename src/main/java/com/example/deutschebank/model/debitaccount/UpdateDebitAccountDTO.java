package com.example.deutschebank.model.debitaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateDebitAccountDTO {
    public UUID id;
    public String iban;
    public String debitStatus;
    public BigDecimal balance;
    public BigDecimal depositInterest;
    public BigDecimal creditLine;
    public LocalDateTime startFrom;
}
