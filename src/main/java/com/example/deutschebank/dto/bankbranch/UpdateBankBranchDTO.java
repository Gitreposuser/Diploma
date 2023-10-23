package com.example.deutschebank.dto.bankbranch;

import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.enums.BranchStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateBankBranchDTO {
    private UUID id;
    private Integer branchNumber;
    private BranchStatus branchStatus;
    private Location location;
    private String generalPhone;
    private String hotLine;
    private Boolean active;
}
