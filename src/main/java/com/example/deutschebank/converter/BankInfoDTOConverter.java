package com.example.deutschebank.converter;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.model.bankinfo.CreateUpdateBankInfoDTO;
import com.example.deutschebank.model.bankinfo.GetBankInfoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankInfoDTOConverter {
    private final ModelMapper modelMapper;

    public CreateUpdateBankInfoDTO convertCreateUpdateToDTO(BankInfo bankInfo) {
        CreateUpdateBankInfoDTO bankInfoDTO = modelMapper.map(bankInfo,
                CreateUpdateBankInfoDTO.class);
        return bankInfoDTO;
    }

    public BankInfo convertCreateUpdateDTOToBankInfo(CreateUpdateBankInfoDTO bankInfoDTO) {
        BankInfo bankInfo = modelMapper.map(bankInfoDTO, BankInfo.class);
        return bankInfo;
    }

    public GetBankInfoDTO convertBankInfoToGetDTO(BankInfo bankInfo) {
        GetBankInfoDTO bankInfoDTO = modelMapper.map(bankInfo,
                GetBankInfoDTO.class);
        return bankInfoDTO;
    }

    public BankInfo convertGetDTOToBankInfo(GetBankInfoDTO bankInfoDTO) {
        BankInfo bankInfo = modelMapper.map(bankInfoDTO, BankInfo.class);
        return bankInfo;
    }
}