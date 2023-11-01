package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.dto.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.dto.workdetail.UpdateWorkDetailDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface WorkDetailService {
    void createWorkDetail(CreateWorkDetailDTO createDTO);

    GetWorkDetailDTO getWorkDetailById(UUID uuid);

    List<GetWorkDetailDTO> getAllWorkDetails();

    void updateWorkDetailById(UpdateWorkDetailDTO updateDTO);

    @Transactional
    void deleteWorkDetailById(UUID uuid);
}