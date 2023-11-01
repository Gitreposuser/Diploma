package com.example.deutschebank.dto.bankaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GetBankAccountDTO {
    private int id;
    private String name;
    private String iban;
    private BigDecimal balance;
    private BigDecimal capitalization;
    private String owner;
    private String group;
    private String logoUrl;
    private LocalDateTime created;
    private LocalDateTime updated;
}