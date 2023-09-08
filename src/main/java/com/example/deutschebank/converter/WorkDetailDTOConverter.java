package com.example.deutschebank.converter;

import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.model.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkDetailDTOConverter {
    private final ModelMapper modelMapper;

    public CreateWorkDetailDTO convertWorkDetailToCreateUpdateDTO(WorkDetail workDetail) {
        CreateWorkDetailDTO workDetailDTO = modelMapper.map(workDetail,
                CreateWorkDetailDTO.class);
        return workDetailDTO;
    }

    public WorkDetail convertCreateUpdateToWorkDetail(CreateWorkDetailDTO createUpdateDTO) {
        WorkDetail workDetail = modelMapper.map(createUpdateDTO,
                WorkDetail.class);
        return workDetail;
    }

    public GetWorkDetailDTO convertWorkDetailToGetDTO(WorkDetail workDetail) {
        GetWorkDetailDTO getWorkDetailDTO = modelMapper.map(workDetail,
                GetWorkDetailDTO.class);
        return getWorkDetailDTO;
    }

    public List<GetWorkDetailDTO> convertWorkDetailsToGetDTOs(List<WorkDetail> workDetails) {
        List<GetWorkDetailDTO> getWorkDetailsDTOs = new LinkedList<>();
        for (WorkDetail detail : workDetails) {
            getWorkDetailsDTOs.add(modelMapper.map(detail,
                    GetWorkDetailDTO.class));
        }
        return getWorkDetailsDTOs;
    }

    public WorkDetail convertGetDTOToWorkDetail(GetWorkDetailDTO getDTO) {
        WorkDetail workDetail = modelMapper.map(getDTO, WorkDetail.class);
        return workDetail;
    }

    public UpdateWorkDetailDTO convertWorkDetailToUpdateDTO(WorkDetail workDetail) {
        UpdateWorkDetailDTO updateDTO = modelMapper.map(workDetail,
                UpdateWorkDetailDTO.class);
        return updateDTO;
    }

    public WorkDetail convertUpdateDTOToWorkDetail(UpdateWorkDetailDTO updateDTO) {
        WorkDetail workDetail = modelMapper.map(updateDTO,
                WorkDetail.class);
        return workDetail;
    }
}