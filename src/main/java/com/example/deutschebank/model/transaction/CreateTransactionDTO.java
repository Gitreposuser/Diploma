package com.example.deutschebank.model.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDTO {
    public String emitterIban;
    public String receiverIban;
    public BigDecimal amount;
}
