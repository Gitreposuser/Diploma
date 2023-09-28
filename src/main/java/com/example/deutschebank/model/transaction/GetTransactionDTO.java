package com.example.deutschebank.model.transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetTransactionDTO {
    private UUID id;
    private String emitterIban;
    private String receiverIban;
    private BigDecimal amount;
    private LocalDateTime created;
}
