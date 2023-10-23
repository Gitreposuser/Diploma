package com.example.deutschebank.dto.bankbranch;

import com.example.deutschebank.dto.location.CreateLocationDTO;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.enums.BranchStatus;
import lombok.Data;

@Data
public class CreateBankBranchDTO {
    private Integer branchNumber;
    private BranchStatus branchStatus;
    private Location location;
    private String generalPhone;
    private String hotLine;
    private Boolean active;
}