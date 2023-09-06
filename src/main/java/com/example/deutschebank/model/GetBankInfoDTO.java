package com.example.deutschebank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetBankInfoDTO {
    public String name;
    public String iban;
    public BigDecimal balance;
    public BigDecimal capitalization;
    public String owner;
    public String group;
    public byte[] logo;
    public LocalDateTime created;
}