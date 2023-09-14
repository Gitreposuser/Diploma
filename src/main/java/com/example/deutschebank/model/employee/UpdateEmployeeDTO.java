package com.example.deutschebank.model.employee;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateEmployeeDTO {
    public UUID id;
    public UUID personalDetailId;
    public UUID workDetailId;
    public UUID locationId;
    public UUID branchId;
}
