package com.example.deutschebank.controller;

import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.service.interfaces.EmployeeService;
import lombok.NoArgsConstructor;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @GetMapping(value = "/generate")
    @ResponseStatus(HttpStatus.OK)
    public String generateEmployee() {
        String result = employeeService.generateEmployees();
        log.info("Generate random employees");
        return result;
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

    @GetMapping(value = "/find-all")
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

    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String testRequest() {
        String text = "Test successful";
        log.info(text);
        return text;
    }
}