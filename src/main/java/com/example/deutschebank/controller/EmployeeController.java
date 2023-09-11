package com.example.deutschebank.controller;

import com.example.deutschebank.model.employee.CreateEmployeeDTO;
import com.example.deutschebank.model.employee.GetEmployeeDTO;
import com.example.deutschebank.model.employee.UpdateEmployeeDTO;
import com.example.deutschebank.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateEmployeeDTO> createEmployee(@RequestBody CreateEmployeeDTO createDTO) {
        employeeService.createEmployee(createDTO);
        return ResponseEntity.status(HttpStatus.OK).body(createDTO);
    }

    @GetMapping(value = "/get/by-id/{uuid}")
    public ResponseEntity<GetEmployeeDTO> findEmployeeById(@PathVariable UUID uuid) {
        GetEmployeeDTO getDTO = employeeService.getEmployee(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetEmployeeDTO>> getAllEmployee() {
        List<GetEmployeeDTO> getAllDTOs = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by-id")
    public void updateEmployee(@RequestBody UpdateEmployeeDTO updateDTO) {
        employeeService.updateEmployee(updateDTO);
    }

    @DeleteMapping(value = "/delete/by-id/{uuid}")
    public void deleteEmployee(@PathVariable UUID uuid) {
        employeeService.deleteEmployee(uuid);
    }
}