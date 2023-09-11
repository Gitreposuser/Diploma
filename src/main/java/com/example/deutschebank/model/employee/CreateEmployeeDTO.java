package com.example.deutschebank.model.employee;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateEmployeeDTO {
    public UUID personalDetailId;
    public UUID workDetailId;
    public UUID locationId;
    public UUID branchId;
    public Boolean active;
}
