package com.example.deutschebank.model.bankbranch;

import com.example.deutschebank.entity.Location;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetBankBranchDTO {
    public UUID id;
    public Integer branchNumber;
    public String status;
    public UUID locationId;
    //public Location locationId;
    public String generalPhone;
    public String hotLine;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}