package com.example.deutschebank.dto.creditaccount;

import com.example.deutschebank.entity.Client;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UpdateCreditAccountDTO {
    @NotNull(message = "Id should not be null!")
    private UUID id;

    @NotNull(message = "Client should not be null!")
    private Client client;

    @NotNull(message = "Reason should not be null!")
    @NotBlank(message = "Reason should not be empty!")
    private String reason;

    @NotNull(message = "Credit status should not be null!")
    @NotBlank(message = "Credit status should not be empty!")
    private String creditStatus;

    @NotNull(message = "Debt should not be null!")
    @Min(value = 1, message = "Debt should start from 1!")
    private BigDecimal debt;

    @NotNull(message = "Loan interest should not be null!")
    @Min(value = 1, message = "Loan interest should start from 1!")
    private BigDecimal loanInterest;

    @NotNull(message = "Active should not be null!")
    private Boolean active;
}