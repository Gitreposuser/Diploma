package com.example.deutschebank.dto.report;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class GetReportTransactionsByFullNameDTO {
    private UUID clientId;
    private String emitterFirstName;
    private String emitterLastName;
    private String emitterPhoneNumber;
    private String emitterEmail;
    private String emitterIban;
    private BigDecimal balance;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal averageAmount;
}
