package com.example.deutschebank.dto.creditaccount;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetCreditAccountInfoDTO {
    private UUID id;
    private String clientFirstName;
    private String clientLastName;
    private String reason;
    private String creditStatus;
    private BigDecimal debt;
    private BigDecimal loanInterest;
    private Boolean active;
    private LocalDateTime created;
    private LocalDateTime updated;
}
