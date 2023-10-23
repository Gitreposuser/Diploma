package com.example.deutschebank.dto.employee;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import com.example.deutschebank.entity.WorkDetail;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateEmployeeDTO {
    private UUID id;
    private PersonalDetail personalDetail;
    private WorkDetail workDetail;
    private Location location;
    private BankBranch bankBranch;
    private Boolean active;
}
