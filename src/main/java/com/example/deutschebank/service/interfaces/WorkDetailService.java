package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.WorkDetail;

import java.util.UUID;

public interface WorkDetailService {
    WorkDetail createWorkDetail(WorkDetail workDetail);

    void updateWorkDetail(UUID uuid, WorkDetail workDetail);

    WorkDetail getWorkDetail(UUID uuid);

    void deleteWorkDetail(UUID uuid);
}
