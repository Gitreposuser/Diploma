package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.model.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;

import java.util.List;
import java.util.UUID;

public interface WorkDetailService {
    void createWorkDetail(CreateWorkDetailDTO createDTO);

    GetWorkDetailDTO getWorkDetailById(UUID uuid);

    List<GetWorkDetailDTO> getAllWorkDetails();

    void updateWorkDetailById(UpdateWorkDetailDTO updateDTO);

    void deleteWorkDetailById(UUID uuid);
}