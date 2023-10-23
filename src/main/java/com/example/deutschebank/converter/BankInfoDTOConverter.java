package com.example.deutschebank.converter;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.dto.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.UpdateBankInfoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankInfoDTOConverter {
    private final ModelMapper modelMapper;

    public BankInfo convertCreateDTOToBankInfo(CreateBankInfoDTO createDTO) {
        return modelMapper.map(createDTO, BankInfo.class);
    }

    public CreateBankInfoDTO convertBankInfoToCreateDTO(BankInfo bankInfo) {
        return modelMapper.map(bankInfo, CreateBankInfoDTO.class);
    }

    public GetBankInfoDTO convertBankInfoToGetDTO(BankInfo bankInfo) {
        return modelMapper.map(bankInfo, GetBankInfoDTO.class);
    }

    public BankInfo convertUpdateDTOToBankInfo(UpdateBankInfoDTO updateDTO) {
        return modelMapper.map(updateDTO, BankInfo.class);
    }
}