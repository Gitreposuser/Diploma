package com.example.deutschebank.dto.employee;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import com.example.deutschebank.entity.WorkDetail;
import lombok.Data;

@Data
public class CreateEmployeeDTO {
    private PersonalDetail personalDetail;
    private WorkDetail workDetail;
    private Location location;
    private BankBranch bankBranch;
    private Boolean active;
}
