package com.example.deutschebank.converter;

import com.example.deutschebank.entity.PersonalDetail;
import com.example.deutschebank.model.personaldetail.CreatePersonalDetailDTO;
import com.example.deutschebank.model.personaldetail.GetPersonalDetailDTO;
import com.example.deutschebank.model.personaldetail.UpdatePersonalDetailDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonalDetailDTOConverter {
    private final ModelMapper modelMapper;

    public PersonalDetail convertCreateDTOToPersonalDetail(CreatePersonalDetailDTO createDTO) {
        return modelMapper.map(createDTO, PersonalDetail.class);
    }

    public GetPersonalDetailDTO convertPersonalDetailToGetDTO(PersonalDetail personalDetail) {
        return modelMapper.map(personalDetail, GetPersonalDetailDTO.class);
    }

    public List<GetPersonalDetailDTO> convertPersonalDetailsToGetDTOs(List<PersonalDetail> personalDetails) {
        List<GetPersonalDetailDTO> getDTOs = new LinkedList<>();
        for(PersonalDetail detail : personalDetails) {
            getDTOs.add(modelMapper.map(detail, GetPersonalDetailDTO.class));
        }
        return getDTOs;
    }

    public PersonalDetail convertUpdateDTOToPersonalDetail(UpdatePersonalDetailDTO updateDTO) {
        return modelMapper.map(updateDTO, PersonalDetail.class);
    }
}
