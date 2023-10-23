package com.example.deutschebank.dto.client;

import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import lombok.Data;

import java.time.Period;
import java.util.UUID;

@Data
public class CreateClientDTO {
    private PersonalDetail personalDetail;
    private DebitAccount debitAccount;
    private Location location;
    private Employee employee;
    private Boolean active;
}
