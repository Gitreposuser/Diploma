package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.personaldetail.CreatePersonalDetailDTO;
import com.example.deutschebank.dto.personaldetail.GetPersonalDetailDTO;
import com.example.deutschebank.dto.personaldetail.UpdatePersonalDetailDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface PersonalDetailService {
    void createPersonalDetail(CreatePersonalDetailDTO createDTO);

    GetPersonalDetailDTO getPersonalDetail(UUID uuid);

    List<GetPersonalDetailDTO> getAllPersonalDetails();

    void updatePersonalDetail(UpdatePersonalDetailDTO updateDTO);

    @Transactional
    void deletePersonalDetail(UUID uuid);
}
