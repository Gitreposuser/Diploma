package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.repository.WorkDetailRepository;
import com.example.deutschebank.service.interfaces.WorkDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkDetailServiceImpl implements WorkDetailService {
    public final WorkDetailRepository workDetailRepository;

    @Override
    public WorkDetail createWorkDetail(WorkDetail workDetail) {
        return workDetailRepository.save(workDetail);
    }

    @Override
    public void updateWorkDetail(WorkDetail workDetail) {
        workDetailRepository.save(workDetail);
    }

    @Override
    public WorkDetail getWorkDetail(UUID uuid) {
        return workDetailRepository.getReferenceById(uuid);
    }

    @Override
    public void deleteWorkDetail(UUID uuid) {
        if (workDetailRepository.existsById(uuid)) {
            workDetailRepository.deleteById(uuid);
        }
    }
}