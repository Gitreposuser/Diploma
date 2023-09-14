package com.example.deutschebank.model.workdetail;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetWorkDetailDTO {
    public UUID id;
    public String position;
    public String status;
    public BigDecimal salary;
    public String workPhone;
    public String workEmail;
    public LocalDateTime startFrom;
    public LocalDateTime endAt;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}
