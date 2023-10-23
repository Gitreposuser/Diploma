package com.example.deutschebank.dto.bankbranch;

import com.example.deutschebank.entity.enums.BranchStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetBankBranchInfoDTO {
    private UUID id;
    private Integer branchNumber;
    private BranchStatus branchStatus;
    private String generalPhone;
    private String hotLine;
    private String country;
    private String city;
    private String street;
    private Integer houseNumber;
    private Boolean active;
}