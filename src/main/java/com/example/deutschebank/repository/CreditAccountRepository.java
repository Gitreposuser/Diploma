package com.example.deutschebank.repository;

import com.example.deutschebank.entity.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount,
        UUID> {
    @Query("SELECT ca FROM CreditAccount ca " +
            "WHERE CONCAT(ca.client.personalDetail.firstName, ' ', " +
            "ca.client.personalDetail.lastName) = :fullName ")
    List<CreditAccount> getCreditAccountsByFullName(String fullName);

    @Query("SELECT ca FROM CreditAccount ca " +
            "WHERE ca.active = true ")
    List<CreditAccount> getAllActiveCreditAccounts();
}
