package com.example.deutschebank.model.debitaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetDebitAccountDTO {
    private UUID id;
    private String iban;
    private String debitStatus;
    private BigDecimal balance;
    private BigDecimal depositInterest;
    private BigDecimal creditLine;
    private LocalDateTime startFrom;
    private Boolean active;
    private LocalDateTime created;
    private LocalDateTime updated;
}
