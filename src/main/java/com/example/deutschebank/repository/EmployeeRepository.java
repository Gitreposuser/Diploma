package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT em " +
            "FROM Employee em " +
            "WHERE em.id = :id")
    Optional<Employee> findEmployeeById(UUID id);

    @Query("SELECT pd.firstName, pd.lastName " +
            "FROM Employee em " +
            "JOIN PersonalDetail pd " +
            "ON em.personalDetailId = pd.id " +
            "WHERE pd.firstName = :firstName AND pd.lastName = :lastName")
    Optional<Employee> findEmployeeByFullName(String firstName,
                                              String lastName);
}