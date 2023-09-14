package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.model.personaldetail.CreatePersonalDetailDTO;
import com.example.deutschebank.model.personaldetail.GetPersonalDetailDTO;
import com.example.deutschebank.model.personaldetail.UpdatePersonalDetailDTO;

import java.util.List;
import java.util.UUID;

public interface PersonalDetailService {
    void createPersonalDetail(CreatePersonalDetailDTO createDTO);

    GetPersonalDetailDTO getPersonalDetail(UUID uuid);

    List<GetPersonalDetailDTO> getAllPersonalDetails();

    void updatePersonalDetail(UpdatePersonalDetailDTO updateDTO);

    void deletePersonalDetail(UUID uuid);
}
