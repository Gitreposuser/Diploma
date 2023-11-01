package com.example.deutschebank.dto.bankaccount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Data
public class CreateBankAccountDTO {
    @NotNull(message = "Name should not be null!")
    @NotBlank(message = "Name should not be empty!")
    @Size(min = 2, message = "Name should be at least 2 " +
            "characters!")
    private String name;

    @NotNull(message = "Balance should not be empty!")
    @Min(value = 0, message = "Balance should be greater than 0!")
    private BigDecimal balance;

    @NotNull(message = "Capitalization should not be empty!")
    @Min(value = 0, message = "Capitalization should be greater than 0!")
    private BigDecimal capitalization;

    @NotNull(message = "Owner should not be null!")
    @NotBlank(message = "should not be empty!")
    @Min(value = 2, message = "Owner should contain at least 2 characters!")
    private String owner;

    @NotNull(message = "Group should not be null!")
    @NotBlank(message = "Group should not be empty!")
    private String group;

    @NotNull(message = "Logo URL should not be null!")
    @NotBlank(message = "Logo URL should not be empty!")
    @URL(message = "Logo URL invalid!")
    private String logoUrl;
}