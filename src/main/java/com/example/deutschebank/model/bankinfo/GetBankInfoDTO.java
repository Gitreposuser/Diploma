package com.example.deutschebank.model.bankinfo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GetBankInfoDTO {
    public int id;
    public String name;
    public String iban;
    public BigDecimal balance;
    public BigDecimal capitalization;
    public String owner;
    public String group;
    public String logoUrl;
    public byte[] logo;
    public LocalDateTime created;
    public LocalDateTime updated;
}