package com.example.deutschebank.model.creditaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateCreditAccountDTO {
    private UUID id;
    private UUID clientId;
    private String creditStatus;
    private BigDecimal debt;
    private BigDecimal loanInterest;
    private LocalDateTime startFrom;
}
