package com.example.deutschebank.repository;

import com.example.deutschebank.entity.PersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PersonalDetailRepository extends JpaRepository<PersonalDetail,
        UUID> {
}
