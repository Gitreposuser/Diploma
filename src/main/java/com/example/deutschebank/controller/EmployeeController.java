package com.example.deutschebank.controller;

import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.service.interfaces.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PostMapping(value = "/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployeeById(@PathVariable UUID id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping(value = "/find/full-name/")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployeeByFullName(
            @RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployeeByFullName(firstName, lastName);
    }

    @GetMapping(value = "/find_all")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> deleteEmployee(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
        return ResponseEntity.ok().build();
    }
}