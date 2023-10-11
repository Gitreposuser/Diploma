package com.example.deutschebank.dto.employee;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateEmployeeDTO {
    private UUID personalDetailId;
    private UUID workDetailId;
    private UUID locationId;
    private UUID branchId;
    private Boolean active;
}
