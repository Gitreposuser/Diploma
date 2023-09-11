package com.example.deutschebank.model.bankbranch;

import com.example.deutschebank.entity.Location;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateBankBranchDTO {
    public UUID id;
    public Integer branchNumber;
    public String status;
    public UUID locationId;
    //public Location location;
    public String generalPhone;
    public String hotLine;
    public Boolean active;
}
