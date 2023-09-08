package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.model.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;

import java.util.List;
import java.util.UUID;

public interface WorkDetailService {
    CreateWorkDetailDTO createWorkDetail(CreateWorkDetailDTO createUpdateDTO);

    GetWorkDetailDTO getWorkDetail(UUID uuid);

    List<GetWorkDetailDTO> getAllWorkDetails();

    /*
    void updateWorkDetail(CreateUpdateWorkDetailDTO createUpdateDTO,
                          UUID uuid);


     */
    void updateWorkDetail(UpdateWorkDetailDTO updateDTO);

    void deleteWorkDetail(UUID uuid);
}