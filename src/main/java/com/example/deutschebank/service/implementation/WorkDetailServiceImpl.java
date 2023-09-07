package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.WorkDetailDTOConverter;
import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.exception.BadEmailException;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.workdetail.CreateUpdateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.repository.WorkDetailRepository;
import com.example.deutschebank.service.interfaces.WorkDetailService;
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
    public CreateUpdateWorkDetailDTO createWorkDetail(CreateUpdateWorkDetailDTO
                                                                  createUpdateDTO) {
        if (workDetailRepository.existsByWorkEmail(createUpdateDTO.workEmail)) {
            throw new BadEmailException("Cannot update entity!");
        }
        WorkDetail workDetail = workDetailDTOConverter
                .convertCreateUpdateToWorkDetail(createUpdateDTO);
        workDetailRepository.save(workDetail);
        log.info("Entity successfully created.");
        return createUpdateDTO;
    }

    @Override
    public GetWorkDetailDTO getWorkDetail(UUID uuid) {
        WorkDetail workDetail = workDetailRepository.getReferenceById(uuid);
        return workDetailDTOConverter.convertWorkDetailToGetDTO(workDetail);
    }

    @Override
    public List<GetWorkDetailDTO> getAllWorkDetails() {
        List<WorkDetail> workDetails = workDetailRepository.findAll();
        return workDetailDTOConverter.convertWorkDetailsToGetDTOs(workDetails);
    }

    @Override
    public void updateWorkDetail(CreateUpdateWorkDetailDTO createUpdateDTO, UUID uuid) {
        if (workDetailRepository.existsByWorkEmail(createUpdateDTO.workEmail)) {
            throw new BadEmailException("Cannot update entity with id: " + uuid + "!");
        }
        WorkDetail workDetail = workDetailDTOConverter
                .convertCreateUpdateToWorkDetail(createUpdateDTO);
        workDetail.setId(uuid);
        log.info(workDetail.getId().toString());
        workDetailRepository.save(workDetail);
        log.info("Entity with id: " + uuid + " is updated.");
    }

    @Override
    public void deleteWorkDetail(UUID uuid) {
        if (!workDetailRepository.existsById(uuid)) {
            throw new BadOperationException("Cannot delete entity!");
        }
        workDetailRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }
}