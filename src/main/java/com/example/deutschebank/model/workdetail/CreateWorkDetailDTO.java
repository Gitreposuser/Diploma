package com.example.deutschebank.model.workdetail;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateWorkDetailDTO {
    public String position;
    public String status;
    public BigDecimal salary;
    public String workPhone;
    public String workEmail;
    public LocalDateTime startFrom;
    public LocalDateTime endAt;
    public Boolean active;
}
