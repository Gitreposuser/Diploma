package com.example.deutschebank.repository;

import com.example.deutschebank.entity.WorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkDetailRepository extends JpaRepository<WorkDetail, UUID> {
    boolean existsByWorkEmail(String email);
}