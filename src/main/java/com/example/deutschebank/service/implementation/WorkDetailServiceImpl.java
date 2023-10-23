package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.WorkDetailDTOConverter;
import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.exception.BadEmailException;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.dto.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.dto.workdetail.UpdateWorkDetailDTO;
import com.example.deutschebank.repository.WorkDetailRepository;
import com.example.deutschebank.service.interfaces.WorkDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkDetailServiceImpl implements WorkDetailService {
    private final WorkDetailRepository workDetailRepository;
    private final WorkDetailDTOConverter workDetailDTOConverter;

    @Override
    @Transactional
    public void createWorkDetail(CreateWorkDetailDTO createDTO) {
        isEmailExist(createDTO.getWorkEmail());
        WorkDetail workDetail = workDetailDTOConverter
                .convertCreateDTOToWorkDetail(createDTO);
        workDetailRepository.save(workDetail);
        log.info("Work detail created.");
    }

    @Override
    @Transactional
    public GetWorkDetailDTO getWorkDetailById(UUID uuid) {
        isWorkDetailNotExist(uuid);
        WorkDetail workDetail = workDetailRepository.getReferenceById(uuid);
        log.info("Get Work detail by id: " + uuid);
        return workDetailDTOConverter.convertWorkDetailToGetDTO(workDetail);
    }

    @Override
    @Transactional
    public List<GetWorkDetailDTO> getAllWorkDetails() {
        List<WorkDetail> workDetails = workDetailRepository.findAll();
        log.info("Get all work details, quantity: " + workDetails.size());
        return workDetailDTOConverter.convertWorkDetailsToGetDTOs(workDetails);
    }

    @Override
    @Transactional
    public void updateWorkDetailById(UpdateWorkDetailDTO updateDTO) {
        isWorkDetailNotExist(updateDTO.getId());
        WorkDetail workDetail = workDetailDTOConverter
                .convertUpdateDTOToWorkDetail(updateDTO);
        workDetailRepository.save(workDetail);
        log.info("Update work detail with id: " + workDetail.getId());
    }

    @Override
    @Transactional
    public void deleteWorkDetailById(UUID uuid) {
        isWorkDetailNotExist(uuid);
        workDetailRepository.deleteById(uuid);
        log.info("Delete work detail with id: " + uuid);
    }

    private void isEmailExist(String email) {
        if (workDetailRepository.existsByWorkEmail(email)) {
            throw new BadEmailException("Email not unique!");
        }
    }

    private void isWorkDetailNotExist(UUID uuid) {
        if (!workDetailRepository.existsById(uuid)) {
            throw new BadOperationException("Work detail with id: " + uuid +
                    "doesn't exist!");
        }
    }
}