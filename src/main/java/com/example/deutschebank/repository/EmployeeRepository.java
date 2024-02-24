package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT em FROM Employee em " +
            "WHERE CONCAT(em.personalDetail.firstName, ' ', " +
            "em.personalDetail.lastName) = :fullName ")
    Employee getEmployeeByFullName(String fullName);

    @Query("SELECT CONCAT(cl.personalDetail.firstName, ' ', " +
            "cl.personalDetail.lastName) FROM Client cl " +
            "WHERE CONCAT(cl.employee.personalDetail.firstName, ' ', " +
            "cl.employee.personalDetail.lastName) = :fullName ")
    List<String> getEmployeeClientsByFullName(String fullName);

    @Query("SELECT em FROM Employee em " +
            "WHERE em.active = true ")
    List<Employee> getAllActiveEmployee();

    @Modifying
    @Query("UPDATE Employee " +
            "SET active = false " +
            "WHERE id = :uuid ")
    void markEmployeeAsDeleted(UUID uuid);
}