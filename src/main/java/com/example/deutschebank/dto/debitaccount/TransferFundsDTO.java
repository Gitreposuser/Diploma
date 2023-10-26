package com.example.deutschebank.dto.debitaccount;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferFundsDTO {
    private BigDecimal amount;
    private String emitterIban;
    private String receiverIban;
}
