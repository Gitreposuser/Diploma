package com.example.deutschebank.converter;

import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.model.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.model.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.model.bankbranch.UpdateBankBranchDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BankBranchDTOConverter {
    private final ModelMapper modelMapper;

    public BankBranch convertCreateDTOToBankBranch(CreateBankBranchDTO createDTO) {
        return modelMapper.map(createDTO,
                BankBranch.class);
    }

    public GetBankBranchDTO convertBankBranchToGetDTO(BankBranch bankBranch) {
        return modelMapper.map(bankBranch, GetBankBranchDTO.class);
    }

/*
    public GetBankBranchDTO convertBankBranchToGetDTO(BankBranch bankBranch) {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(BankBranch.class, GetBankBranchDTO.class)
                .addMapping(BankBranch::getLocationId, GetBankBranchDTO::setLocationId);
        return mapper.map(bankBranch,
                GetBankBranchDTO.class);
    }

 */

    public List<GetBankBranchDTO> convertBankBranchesToGetDTOs(List<BankBranch> bankBranches) {
        List<GetBankBranchDTO> getDTOs = new LinkedList<>();
        for (BankBranch detail : bankBranches) {
            getDTOs.add(modelMapper.map(detail,
                    GetBankBranchDTO.class));
        }
        return getDTOs;
    }

    public BankBranch convertUpdateDTOToBankBranch(UpdateBankBranchDTO updateDTO) {
        return modelMapper.map(updateDTO,
                BankBranch.class);
    }
}
