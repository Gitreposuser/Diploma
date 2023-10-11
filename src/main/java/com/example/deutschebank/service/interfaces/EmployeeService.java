package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.employee.CreateEmployeeDTO;
import com.example.deutschebank.dto.employee.GetEmployeeDTO;
import com.example.deutschebank.dto.employee.UpdateEmployeeDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    void createEmployee(CreateEmployeeDTO createDTO);

    GetEmployeeDTO getEmployeeById(UUID uuid);

    List<GetEmployeeDTO> getAllEmployees();

    void updateEmployeeById(UpdateEmployeeDTO updateDTO);

    void deleteEmployeeById(UUID uuid);
}