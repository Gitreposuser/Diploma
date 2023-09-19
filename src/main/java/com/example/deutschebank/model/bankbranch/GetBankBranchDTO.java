package com.example.deutschebank.model.bankbranch;

import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.enums.BranchStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetBankBranchDTO {
    public UUID id;
    public Integer branchNumber;
    public BranchStatus branchStatus;
    public Location location;
    public String generalPhone;
    public String hotLine;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}