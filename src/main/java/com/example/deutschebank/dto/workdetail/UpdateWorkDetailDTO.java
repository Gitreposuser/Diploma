package com.example.deutschebank.dto.workdetail;

import com.example.deutschebank.entity.enums.WorkStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateWorkDetailDTO {
    private UUID id;
    private String position;
    private WorkStatus workStatus;
    private BigDecimal salary;
    private String workPhone;
    private String workEmail;
    private LocalDateTime startFrom;
    private LocalDateTime endAt;
}
