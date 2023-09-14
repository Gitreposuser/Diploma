package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.WorkDetailDTOConverter;
import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.exception.BadEmailException;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;
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
        checkEmail(createDTO.workEmail);
        WorkDetail workDetail = workDetailDTOConverter
                .convertCreateDTOToWorkDetail(createDTO);
        workDetailRepository.save(workDetail);
        log.info("Entity successfully created.");
    }

    @Override
    public GetWorkDetailDTO getWorkDetailById(UUID uuid) {
        checkIfNotExist(uuid);
        WorkDetail workDetail = workDetailRepository.getReferenceById(uuid);
        return workDetailDTOConverter.convertWorkDetailToGetDTO(workDetail);
    }

    @Override
    public List<GetWorkDetailDTO> getAllWorkDetails() {
        List<WorkDetail> workDetails = workDetailRepository.findAll();
        return workDetailDTOConverter.convertWorkDetailsToGetDTOs(workDetails);
    }

    @Override
    @Transactional
    public void updateWorkDetailById(UpdateWorkDetailDTO updateDTO) {
        checkIfNotExist(updateDTO.id);
        checkEmail(updateDTO.getWorkEmail());
        WorkDetail workDetail = workDetailDTOConverter
                .convertUpdateDTOToWorkDetail(updateDTO);
        workDetailRepository.save(workDetail);
        log.info("Entity with id: " + workDetail.getId() + " is updated.");
    }

    @Override
    public void deleteWorkDetailById(UUID uuid) {
        checkIfNotExist(uuid);
        workDetailRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkEmail(String email) {
        if (workDetailRepository.existsByWorkEmail(email)) {
            throw new BadEmailException("Email not unique!");
        }
    }

    private void checkIfNotExist(UUID uuid) {
        if (!workDetailRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}