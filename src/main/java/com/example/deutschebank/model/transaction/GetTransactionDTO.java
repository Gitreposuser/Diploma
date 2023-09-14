package com.example.deutschebank.model.transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetTransactionDTO {
    public UUID id;
    public String emitterIban;
    public String receiverIban;
    public BigDecimal amount;
    public LocalDateTime created;
}
