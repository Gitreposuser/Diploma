package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findEmployeeById(UUID id);
    Optional<Employee> findEmployeeByFullName(String firstName,
                                              String LastName);

}