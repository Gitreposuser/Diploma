package com.example.deutschebank.dto.client;

import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetClientDTO {
    private UUID id;
    private PersonalDetail personalDetail;
    private DebitAccount debitAccount;
    private Location location;
    private Employee employee;
    private Boolean active;
    private LocalDateTime created;
    private LocalDateTime updated;
}
