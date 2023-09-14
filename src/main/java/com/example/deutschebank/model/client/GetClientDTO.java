package com.example.deutschebank.model.client;

import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.PersonalDetail;
import lombok.Data;
import org.apache.catalina.Manager;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetClientDTO {
    public UUID id;
    public Employee employee;
    public DebitAccount debitAccount;
    public PersonalDetail personalDetail;
    public Location location;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}
