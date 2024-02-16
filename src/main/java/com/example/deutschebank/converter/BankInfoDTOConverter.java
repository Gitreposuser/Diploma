package com.example.deutschebank.converter;

import com.example.deutschebank.entity.BankAccount;
import com.example.deutschebank.dto.bankaccount.CreateBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.GetBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.UpdateBankAccountDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankInfoDTOConverter {
    private final ModelMapper modelMapper;

    public BankAccount convertCreateDTOToBankInfo(CreateBankAccountDTO createDTO) {
        return modelMapper.map(createDTO, BankAccount.class);
    }

    public CreateBankAccountDTO convertBankInfoToCreateDTO(BankAccount bankAccount) {
        return modelMapper.map(bankAccount, CreateBankAccountDTO.class);
    }

    public GetBankAccountDTO convertBankInfoToGetDTO(BankAccount bankAccount) {
        return modelMapper.map(bankAccount, GetBankAccountDTO.class);
    }

    public BankAccount convertUpdateDTOToBankInfo(UpdateBankAccountDTO updateDTO) {
        return modelMapper.map(updateDTO, BankAccount.class);
    }
}