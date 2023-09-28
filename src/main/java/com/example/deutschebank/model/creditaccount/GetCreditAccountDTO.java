package com.example.deutschebank.model.creditaccount;

import com.example.deutschebank.entity.Client;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetCreditAccountDTO {
    private UUID id;
    private Client client;
    private String creditStatus;
    private BigDecimal debt;
    private BigDecimal loanInterest;
    private LocalDateTime startFrom;
    private Boolean active;
    private LocalDateTime created;
    private LocalDateTime updated;
}
