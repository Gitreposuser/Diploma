package com.example.deutschebank.mapper;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.model.UpdateBankInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class UpdateBankInfoMapper extends MapperBase<UpdateBankInfoDTO, BankInfo>{
    @Override
    public BankInfo map(UpdateBankInfoDTO updateDTO) {
        BankInfo bankInfo = new BankInfo();
        bankInfo.setName(updateDTO.name);
        bankInfo.setCapitalization(updateDTO.capitalization);
        bankInfo.setOwner(updateDTO.owner);
        bankInfo.setGroup(updateDTO.group);
        bankInfo.setLogo(updateDTO.logo);
        return bankInfo;
    }
}
