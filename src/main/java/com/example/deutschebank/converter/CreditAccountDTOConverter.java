package com.example.deutschebank.converter;

import com.example.deutschebank.entity.CreditAccount;
import com.example.deutschebank.model.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.model.creditaccount.GetCreditAccountDTO;
import com.example.deutschebank.model.creditaccount.UpdateCreditAccountDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreditAccountDTOConverter {
    private final ModelMapper modelMapper;

    public CreditAccount convertCreateDTOToCreditAccount(CreateCreditAccountDTO createDTO) {
        return modelMapper.map(createDTO,
                CreditAccount.class);
    }

    public GetCreditAccountDTO convertCreditAccountToGetDTO(CreditAccount creditAccount) {
        return modelMapper.map(creditAccount,
                GetCreditAccountDTO.class);
    }

    public List<GetCreditAccountDTO> convertCreditAccountToGetDTOs(List<CreditAccount> creditAccounts) {
        List<GetCreditAccountDTO> getDTOs = new LinkedList<>();
        for (CreditAccount detail : creditAccounts) {
            getDTOs.add(modelMapper.map(detail,
                    GetCreditAccountDTO.class));
        }
        return getDTOs;
    }

    public CreditAccount convertUpdateDTOToCreditAccount(UpdateCreditAccountDTO updateDTO) {
        return modelMapper.map(updateDTO,
                CreditAccount.class);
    }
}
