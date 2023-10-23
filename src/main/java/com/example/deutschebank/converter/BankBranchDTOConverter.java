package com.example.deutschebank.converter;

import com.example.deutschebank.dto.bankbranch.GetBankBranchInfoDTO;
import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.UpdateBankBranchDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Slf4j
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

    public GetBankBranchInfoDTO convertBankBranchToGetInfoDTO(BankBranch bankBranch) {
        GetBankBranchInfoDTO getBranchInfoDTO = modelMapper
                .typeMap(BankBranch.class, GetBankBranchInfoDTO.class)
                .addMapping(src -> src.getLocation().getCountry(),
                        GetBankBranchInfoDTO::setCountry)
                .addMapping(src -> src.getLocation().getCity(),
                        GetBankBranchInfoDTO::setCity)
                .addMapping(src -> src.getLocation().getStreet(),
                        GetBankBranchInfoDTO::setStreet)
                .addMapping(src -> src.getLocation().getHouseNumber(),
                        GetBankBranchInfoDTO::setHouseNumber)
                .map(bankBranch);
        return getBranchInfoDTO;
    }

    public List<GetBankBranchDTO> convertBankBranchesToGetDTOs(List<BankBranch> bankBranches) {
        List<GetBankBranchDTO> getDTOs = new LinkedList<>();
        for (BankBranch detail : bankBranches) {
            getDTOs.add(modelMapper.map(detail, GetBankBranchDTO.class));
        }
        return getDTOs;
    }

    public BankBranch convertUpdateDTOToBankBranch(UpdateBankBranchDTO updateDTO) {
        return modelMapper.map(updateDTO, BankBranch.class);
    }
}
