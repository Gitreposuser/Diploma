package com.example.deutschebank.mapper;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.model.CreateBankInfoDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateBankInfoMapper extends MapperBase<CreateBankInfoDTO, BankInfo>{

    @Override
    public BankInfo map(CreateBankInfoDTO createDTO) {
        BankInfo bankInfo = new BankInfo();
        bankInfo.setName(createDTO.name);
        bankInfo.setBalance(new BigDecimal(0));
        bankInfo.setCapitalization(createDTO.capitalization);
        bankInfo.setOwner(createDTO.owner);
        bankInfo.setGroup(createDTO.group);
        bankInfo.setLogo(createDTO.logo);
        return bankInfo;
    }
}