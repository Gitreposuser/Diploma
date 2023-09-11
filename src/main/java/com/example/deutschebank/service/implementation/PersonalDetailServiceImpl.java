package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.PersonalDetailDTOConverter;
import com.example.deutschebank.entity.PersonalDetail;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.personaldetail.CreatePersonalDetailDTO;
import com.example.deutschebank.model.personaldetail.GetPersonalDetailDTO;
import com.example.deutschebank.model.personaldetail.UpdatePersonalDetailDTO;
import com.example.deutschebank.repository.PersonalDetailRepository;
import com.example.deutschebank.service.interfaces.PersonalDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonalDetailServiceImpl implements PersonalDetailService {

    private final PersonalDetailRepository personalDetailRepository;
    private final PersonalDetailDTOConverter personalDetailDTOConverter;

    @Override
    @Transactional
    public CreatePersonalDetailDTO createPersonalDetail(CreatePersonalDetailDTO createDTO) {
        PersonalDetail personalDetail =
                personalDetailDTOConverter.convertCreateDTOToPersonalDetail(createDTO);
        personalDetailRepository.save(personalDetail);
        log.info("Entity successfully created.");
        return createDTO;
    }

    @Override
    public GetPersonalDetailDTO getPersonalDetail(UUID uuid) {
        checkIfNotExist(uuid);
        PersonalDetail personalDetail =
                personalDetailRepository.getReferenceById(uuid);
        return personalDetailDTOConverter.convertPersonalDetailToGetDTO(personalDetail);
    }

    @Override
    public List<GetPersonalDetailDTO> getAllPersonalDetails() {
        List<PersonalDetail> personalDetails =
                personalDetailRepository.findAll();
        return personalDetailDTOConverter.convertPersonalDetailsToGetDTOs(personalDetails);
    }

    @Override
    @Transactional
    public void updatePersonalDetail(UpdatePersonalDetailDTO updateDTO) {
        checkIfNotExist(updateDTO.id);
        PersonalDetail personalDetail =
                personalDetailDTOConverter.convertUpdateDTOToPersonalDetail(updateDTO);
        personalDetailRepository.save(personalDetail);
        log.info("Entity with id: " + personalDetail.getId() + " is updated." );
    }

    @Override
    public void deletePersonalDetail(UUID uuid) {
        checkIfNotExist(uuid);
        personalDetailRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkIfNotExist(UUID uuid) {
        if(!personalDetailRepository.existsById(uuid)) {
            throw new BadOperationException("Cannot update entity! Entity " +
                    "doesn't exist!");
        }
    }
}
