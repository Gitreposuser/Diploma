package com.example.deutschebank.dto.client;

import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateClientDTO {
    @NotNull(message = "Personal detail should not be null!")
    private PersonalDetail personalDetail;

    @NotNull(message = "Debit account detail should not be null!")
    private DebitAccount debitAccount;

    @NotNull(message = "Location should not be null!")
    private Location location;

    @NotNull(message = "Employee should not be null!")
    private Employee employee;

    @NotNull(message = "Active should not be null!")
    private Boolean active;
}
