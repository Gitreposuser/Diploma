package com.example.deutschebank.converter;

import com.example.deutschebank.entity.Employee;
import com.example.deutschebank.model.employee.CreateEmployeeDTO;
import com.example.deutschebank.model.employee.GetEmployeeDTO;
import com.example.deutschebank.model.employee.UpdateEmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeDTOConverter {
    private final ModelMapper modelMapper;

    public Employee convertCreateDTOToEmployee(CreateEmployeeDTO createDTO) {
        return modelMapper.map(createDTO, Employee.class);
    }

    public GetEmployeeDTO convertEmployeeToGetDTO(Employee employee) {
        return modelMapper.map(employee,
                GetEmployeeDTO.class);
    }

    public List<GetEmployeeDTO> convertEmployeeToGetDTOs(List<Employee> employee) {
        List<GetEmployeeDTO> getDTOs = new LinkedList<>();
        for (Employee detail : employee) {
            getDTOs.add(modelMapper.map(detail, GetEmployeeDTO.class));
        }
        return getDTOs;
    }

    public Employee convertUpdateDTOToEmployee(UpdateEmployeeDTO updateDTO) {
        return modelMapper.map(updateDTO, Employee.class);
    }
}
