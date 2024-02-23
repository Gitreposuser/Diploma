package com.example.deutschebank.dto.transaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionDTO {

    @NotNull(message = "Emitter Iban should not be null!")
    @NotBlank(message = "Emitter Iban should not be empty!")
    private String emitterIban;

    @NotNull(message = "Receiver Iban should not be null!")
    @NotBlank(message = "Receiver Iban should not be empty!")
    private String receiverIban;

    @NotNull(message = "Amount should not be null!")
    @Min(value = 1, message = "Amount should start from 1!")
    private BigDecimal amount;
}