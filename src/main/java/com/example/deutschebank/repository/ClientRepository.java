package com.example.deutschebank.repository;

import com.example.deutschebank.entity.Client;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface ClientRepository extends JpaRepository<Client, UUID> {
}