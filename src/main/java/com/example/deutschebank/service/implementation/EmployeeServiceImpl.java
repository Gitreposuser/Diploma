package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.repository.EmployeeRepository;
import com.example.deutschebank.service.interfaces.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    /*
    @Override
    public Employee generateEmployees() {
        Employee employee = new Employee();
        employee.setActive(true);
        return employee;
    }
     */

    @Override
    public String generateEmployees() {
        //Employee employee = new Employee();
        //employee.setActive(true);
        return "Succesfully created";
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(UUID id) {
        Optional<Employee> employee = employeeRepository.findEmployeeById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
        return employee.orElseThrow(() -> {
            String message = "user with id:" + id + " not found";
            log.error(message);
            return new EntityNotFoundException(message);
        });
    }

    public Employee findEmployeeByFullName(String firstName, String lastName) {
        Optional<Employee> employee = employeeRepository
                .findEmployeeByFullName(firstName, lastName);
        if(employee.isPresent()) {
            return employee.get();
        }
        return employee.orElseThrow(() -> {
            String message = "user with first name: " + firstName +
                    " and last name: " + lastName;
            log.error(message);
            return new EntityNotFoundException(message);
        });
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return false;
    }

    @Override
    public void testMethod() {
        employeeRepository.toString();
    }
}