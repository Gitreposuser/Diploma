package com.example.deutschebank.dto.bankbranch;

import com.example.deutschebank.entity.enums.BranchStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateBankBranchDTO {
    private Integer branchNumber;
    private BranchStatus branchStatus;
    private UUID locationId;
    private String generalPhone;
    private String hotLine;
    private Boolean active;
}