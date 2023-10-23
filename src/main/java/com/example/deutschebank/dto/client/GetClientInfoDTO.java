package com.example.deutschebank.dto.client;

import com.example.deutschebank.entity.enums.DebitStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetClientInfoDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Integer age;
    private LocalDateTime birthDate;
    private String Country;
    private String City;
    private String iban;
    private DebitStatus debitStatus;
    private BigDecimal balance;
    private BigDecimal depositInterest;
    private UUID managerId;
    private String managerFirstName;
    private String managerLastName;
}
