package com.example.deutschebank.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBankInfoDTO {
    public String name;
    public BigDecimal capitalization;
    public String owner;
    public String group;
    public byte[] logo;
}