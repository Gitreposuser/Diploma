package com.example.deutschebank.model.bankbranch;

import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.enums.BranchStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateBankBranchDTO {
    public Integer branchNumber;
    public BranchStatus branchStatus;
    public UUID locationId;
    public String generalPhone;
    public String hotLine;
    public Boolean active;
}