package com.example.deutschebank.model.employee;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetEmployeeDTO {
    public UUID id;
    public UUID personalDetailId;
    public UUID workDetailId;
    public UUID locationId;
    public UUID branchId;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}
