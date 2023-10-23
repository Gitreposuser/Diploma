package com.example.deutschebank.dto.creditaccount;

import com.example.deutschebank.entity.Client;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateCreditAccountDTO {
    private Client client;
    private String creditStatus;
    private BigDecimal debt;
    private BigDecimal loanInterest;
    private LocalDateTime startFrom;
    private Boolean active;
}
