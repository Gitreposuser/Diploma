package com.example.deutschebank.model.employee;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateEmployeeDTO {
    private UUID id;
    private UUID personalDetailId;
    private UUID workDetailId;
    private UUID locationId;
    private UUID branchId;
}
