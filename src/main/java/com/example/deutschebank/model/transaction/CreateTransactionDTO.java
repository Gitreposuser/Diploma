package com.example.deutschebank.model.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDTO {
    private String emitterIban;
    private String receiverIban;
    private BigDecimal amount;
}
