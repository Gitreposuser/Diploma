package com.example.deutschebank.model.creditaccount;

import com.example.deutschebank.entity.Client;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetCreditAccountDTO {
    public UUID id;
    public Client client;
    public String creditStatus;
    public BigDecimal debt;
    public BigDecimal loanInterest;
    public LocalDateTime startFrom;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}
