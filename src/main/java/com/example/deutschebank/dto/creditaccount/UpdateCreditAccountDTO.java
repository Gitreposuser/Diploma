package com.example.deutschebank.dto.creditaccount;

import com.example.deutschebank.entity.Client;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UpdateCreditAccountDTO {
    private UUID id;
    private Client client;
    private String reason;
    private String creditStatus;
    private BigDecimal debt;
    private BigDecimal loanInterest;
    private Boolean active;
}
