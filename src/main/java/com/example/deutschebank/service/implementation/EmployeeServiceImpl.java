package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.EmployeeDTOConverter;
import com.example.deutschebank.dto.employee.GetEmployeeClientsDTO;
import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.employee.CreateEmployeeDTO;
import com.example.deutschebank.dto.employee.GetEmployeeDTO;
import com.example.deutschebank.dto.employee.UpdateEmployeeDTO;
import com.example.deutschebank.repository.EmployeeRepository;
import com.example.deutschebank.service.interfaces.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOConverter employeeDTOConverter;

    @Override
    @Transactional
    public void createEmployee(CreateEmployeeDTO createDTO) {
        Employee employee = employeeDTOConverter
                .convertCreateDTOToEmployee(createDTO);
        employeeRepository.save(employee);
        log.info("Create employee.");
    }

    @Override
    @Transactional
    public GetEmployeeDTO getEmployeeById(UUID uuid) {
        isNotExist(uuid);
        Employee employee = employeeRepository.getReferenceById(uuid);
        log.info("Get employee by id: " + uuid);
        return employeeDTOConverter.convertEmployeeToGetDTO(employee);
    }

    @Override
    @Transactional
    public GetEmployeeDTO getEmployeeByFullName(String fullName) {
        Employee employee = employeeRepository.getEmployeeByFullName(fullName);
        isNull(employee);
        log.info("Get employee by full name: " + fullName);
        return employeeDTOConverter.convertEmployeeToGetDTO(employee);
    }

    @Override
    public GetEmployeeClientsDTO getEmployeeClientsByFullName(String fullName) {
        GetEmployeeClientsDTO getDTO = new GetEmployeeClientsDTO();
        getDTO.setClientsFullNames(employeeRepository
                .getEmployeeClientsByFullName(fullName));
        log.info("Get employee clients by full name: " + fullName);
        return getDTO;
    }

    @Override
    @Transactional
    public List<GetEmployeeDTO> getAllActiveEmployees() {
        List<Employee> employees = employeeRepository.getAllActiveEmployee();
        log.info("Get all active employee, quantity: " + employees.size());
        return employeeDTOConverter.convertEmployeeToGetDTOs(employees);
    }

    @Override
    @Transactional
    public List<GetEmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        log.info("Get all employee, quantity: " + employees.size());
        return employeeDTOConverter.convertEmployeeToGetDTOs(employees);
    }

    @Override
    @Transactional
    public void updateEmployeeById(UpdateEmployeeDTO updateDTO) {
        isNotExist(updateDTO.getId());
        Employee employee = employeeDTOConverter
                .convertUpdateDTOToEmployee(updateDTO);
        employeeRepository.save(employee);
        log.info("Update employee by id: " + employee.getId());
    }

    @Override
    @Transactional
    public void markEmployeeAsDeletedById(UUID uuid) {
        isNotExist(uuid);
        Employee employee = employeeRepository.getReferenceById(uuid);
        employee.setActive(false);
        employeeRepository.save(employee);
        log.info("Mark as deleted employee by id: " + uuid);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(UUID uuid) {
        isNotExist(uuid);
        employeeRepository.deleteById(uuid);
        log.info("Delete employee by id: " + uuid);
    }

    private void isNull(Employee employee) {
        if(employee == null) {
            throw new BadOperationException("Employee is null!");
        }
    }

    private void isNotExist(UUID uuid) {
        if(!employeeRepository.existsById(uuid)) {
            throw new BadOperationException("Employee with id: " + uuid +
                    "doesn't exist!");
        }
    }
}