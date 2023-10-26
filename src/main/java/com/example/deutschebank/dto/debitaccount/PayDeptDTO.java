package com.example.deutschebank.dto.debitaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PayDeptDTO {
    private UUID debitAccountId;
    private UUID creditAccountId;
    private BigDecimal amount;
}
