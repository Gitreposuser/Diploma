package com.example.deutschebank.model.workdetail;

import com.example.deutschebank.entity.enums.WorkStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateWorkDetailDTO {
    public UUID id;
    public String position;
    public WorkStatus workStatus;
    public BigDecimal salary;
    public String workPhone;
    public String workEmail;
    public LocalDateTime startFrom;
    public LocalDateTime endAt;
}
