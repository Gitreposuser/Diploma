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

    public WorkDetail convertCreateDTOToWorkDetail(CreateWorkDetailDTO createDTO) {
        return modelMapper.map(createDTO,
                WorkDetail.class);
    }

    public GetWorkDetailDTO convertWorkDetailToGetDTO(WorkDetail workDetail) {
        return modelMapper.map(workDetail,
                GetWorkDetailDTO.class);
    }

    public List<GetWorkDetailDTO> convertWorkDetailsToGetDTOs(List<WorkDetail> workDetails) {
        List<GetWorkDetailDTO> getDTOs = new LinkedList<>();
        for (WorkDetail detail : workDetails) {
            getDTOs.add(modelMapper.map(detail,
                    GetWorkDetailDTO.class));
        }
        return getDTOs;
    }

    public WorkDetail convertUpdateDTOToWorkDetail(UpdateWorkDetailDTO updateDTO) {
        return modelMapper.map(updateDTO,
                WorkDetail.class);
    }
}