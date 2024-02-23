package com.example.deutschebank.dto.employee;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import com.example.deutschebank.entity.WorkDetail;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEmployeeDTO {
    @NotNull(message = "Personal detail should not be null!")
    private PersonalDetail personalDetail;

    @NotNull(message = "Work detail should not be null!")
    private WorkDetail workDetail;

    @NotNull(message = "Location should not be null!")
    private Location location;

    @NotNull(message = "Bank branch should not be null!")
    private BankBranch bankBranch;

    @NotNull(message = "Active should not be null!")
    private Boolean active;
}