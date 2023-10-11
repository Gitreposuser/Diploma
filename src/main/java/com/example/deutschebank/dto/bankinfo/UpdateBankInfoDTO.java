package com.example.deutschebank.dto.bankinfo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateBankInfoDTO {
    private String name;
    private BigDecimal capitalization;
    private String owner;
    private String group;
    private String logoUrl;
}
