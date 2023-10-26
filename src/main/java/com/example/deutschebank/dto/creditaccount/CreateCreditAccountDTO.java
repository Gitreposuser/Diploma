package com.example.deutschebank.dto.creditaccount;

import com.example.deutschebank.entity.Client;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCreditAccountDTO {
    private Client client;
    private String reason;
    private String creditStatus;
    private BigDecimal debt;
    private BigDecimal loanInterest;
    private Boolean active;
}
