package com.example.deutschebank.dto.debitaccount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferFundsDTO {
    @NotNull(message = "Amount should not be null!")
    @Min(value = 1, message = "Amount should start from 1!")
    private BigDecimal amount;

    @NotNull(message = "Emitter iban should not be null!")
    @NotBlank(message = "Emitter iban should not be empty!")
    private String emitterIban;

    @NotNull(message = "Receiver iban should not be null!")
    @NotBlank(message = "Receiver iban should not be empty!")
    private String receiverIban;
}