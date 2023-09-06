package com.example.deutschebank.mapper;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.model.GetBankInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class GetBankInfoMapper extends MapperBase<BankInfo, GetBankInfoDTO>{

    @Override
    public GetBankInfoDTO map(BankInfo inputDTO) {
        GetBankInfoDTO getDTO = new GetBankInfoDTO();
        getDTO.name = inputDTO.getName();
        getDTO.iban = inputDTO.getIban();
        getDTO.balance = inputDTO.getBalance();
        getDTO.capitalization = inputDTO.getCapitalization();
        getDTO.owner = inputDTO.getOwner();
        getDTO.group = inputDTO.getGroup();
        getDTO.logo = inputDTO.getLogo();
        getDTO.created = inputDTO.getCreated();
        return getDTO;
    }
}