package com.example.deutschebank.model.debitaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateDebitAccountDTO {
    public String iban;
    public String debitStatus;
    public BigDecimal balance;
    public BigDecimal depositInterest;
    public BigDecimal creditLine;
    public LocalDateTime startFrom;
    public Boolean active;
}
