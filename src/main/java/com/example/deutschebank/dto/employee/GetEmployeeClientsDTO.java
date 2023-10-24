package com.example.deutschebank.dto.employee;

import lombok.Data;

import java.util.List;

@Data
public class GetEmployeeClientsDTO {
    private List<String> clientsFullNames;
}
