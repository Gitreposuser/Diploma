package com.example.deutschebank.dto.bankbranch;

import com.example.deutschebank.entity.enums.BranchStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class GetBranchCityDTO {
    private UUID id;
    private Integer branchNumber;
    private BranchStatus branchStatus;
    private String city;
    private Boolean active;
}
