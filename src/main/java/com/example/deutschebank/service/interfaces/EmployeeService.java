package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.Employee;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    //Employee generateEmployees();
    String generateEmployees();
    List<Employee> findAll();
    Employee findEmployeeById(UUID id);
    Employee findEmployeeByFullName(String firstName, String lastName);
    boolean deleteEmployee(Employee employee);
    public void testMethod();

}