package com.example.deutschebank.model.creditaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateCreditAccountDTO {
    public UUID id;
    public UUID clientId;
    public String creditStatus;
    public BigDecimal debt;
    public BigDecimal loanInterest;
    public LocalDateTime startFrom;
}
