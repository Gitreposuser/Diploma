package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.model.employee.CreateEmployeeDTO;
import com.example.deutschebank.model.employee.GetEmployeeDTO;
import com.example.deutschebank.model.employee.UpdateEmployeeDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    CreateEmployeeDTO createEmployee(CreateEmployeeDTO createDTO);

    GetEmployeeDTO getEmployee(UUID uuid);

    List<GetEmployeeDTO> getAllEmployees();

    void updateEmployee(UpdateEmployeeDTO updateDTO);

    void deleteEmployee(UUID uuid);
}