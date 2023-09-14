package com.example.deutschebank.model.employee;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import com.example.deutschebank.entity.WorkDetail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetEmployeeDTO {
    public UUID id;
    public PersonalDetail personalDetail;
    public WorkDetail workDetail;
    public Location location;
    public BankBranch bankBranch;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}
