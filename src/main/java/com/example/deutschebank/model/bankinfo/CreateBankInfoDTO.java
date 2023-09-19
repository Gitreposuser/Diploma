package com.example.deutschebank.model.bankinfo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateBankInfoDTO {
    public String name;
    public BigDecimal capitalization;
    public String owner;
    public String group;
    public String logoUrl;
}