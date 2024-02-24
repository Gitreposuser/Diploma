package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Client;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@NonNullApi
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query("SELECT cl FROM Client cl " +
            "WHERE CONCAT(cl.personalDetail.firstName, ' ', cl.personalDetail.lastName) = :fullName ")
    Client getClientByFullName(String fullName);

    @Query("SELECT cl.debitAccount.iban FROM Client cl " +
            "WHERE CONCAT(cl.personalDetail.firstName, ' ', cl.personalDetail.lastName) = :fullName ")
    String getClientIbanByFullName(String fullName);

    @Query("SELECT cl FROM Client cl " +
            "WHERE cl.active = true ")
    List<Client> getAllActiveClients();
}