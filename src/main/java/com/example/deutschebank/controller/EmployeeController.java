package com.example.deutschebank.controller;

import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.service.interfaces.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Employee createEmployee(@RequestBody Employee employee) {
        return  employeeService.createEmployee(employee);
    }

    @PostMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployeeById(@PathVariable UUID id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/find/full-name/")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployeeByFullName(
            @RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployeeByFullName(firstName, lastName);
    }
}