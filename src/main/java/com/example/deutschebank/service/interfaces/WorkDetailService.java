package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.exception.BadEmailException;
import com.example.deutschebank.model.workdetail.CreateUpdateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;

import java.util.List;
import java.util.UUID;

public interface WorkDetailService {
    CreateUpdateWorkDetailDTO createWorkDetail(CreateUpdateWorkDetailDTO createUpdateDTO);

    GetWorkDetailDTO getWorkDetail(UUID uuid);

    List<GetWorkDetailDTO> getAllWorkDetails();

    void updateWorkDetail(CreateUpdateWorkDetailDTO createUpdateDTO,
                          UUID uuid);

    void deleteWorkDetail(UUID uuid);
}