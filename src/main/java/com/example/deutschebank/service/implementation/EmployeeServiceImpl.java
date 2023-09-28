package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.EmployeeDTOConverter;
import com.example.deutschebank.converter.WorkDetailDTOConverter;
import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.employee.CreateEmployeeDTO;
import com.example.deutschebank.model.employee.GetEmployeeDTO;
import com.example.deutschebank.model.employee.UpdateEmployeeDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;
import com.example.deutschebank.repository.EmployeeRepository;
import com.example.deutschebank.service.interfaces.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        log.info("Entity successfully created.");
    }

    @Override
    public GetEmployeeDTO getEmployeeById(UUID uuid) {
        checkIfNotExist(uuid);
        Employee employee = employeeRepository.getReferenceById(uuid);
        return employeeDTOConverter.convertEmployeeToGetDTO(employee);
    }

    @Override
    public List<GetEmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeDTOConverter.convertEmployeeToGetDTOs(employees);
    }

    @Override
    @Transactional
    public void updateEmployeeById(UpdateEmployeeDTO updateDTO) {
        checkIfNotExist(updateDTO.getId());
        Employee employee = employeeDTOConverter
                .convertUpdateDTOToEmployee(updateDTO);
        employeeRepository.save(employee);
        log.info("Entity with id: " + employee.getId() + " is updated.");
    }

    @Override
    public void deleteEmployeeById(UUID uuid) {
        checkIfNotExist(uuid);
        employeeRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkIfNotExist(UUID uuid) {
        if(!employeeRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}