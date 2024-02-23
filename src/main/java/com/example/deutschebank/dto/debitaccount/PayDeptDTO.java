package com.example.deutschebank.dto.debitaccount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PayDeptDTO {
    @NotNull(message = "UUID should not null!")
    private UUID debitAccountId;

    @NotNull(message = "UUID should not null!")
    private UUID creditAccountId;

    @NotNull(message = "Amount should not be null!")
    @Min(value = 1, message = "Amount should start from 1!")
    private BigDecimal amount;
}
