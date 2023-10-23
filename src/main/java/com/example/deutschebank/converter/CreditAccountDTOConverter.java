package com.example.deutschebank.converter;

import com.example.deutschebank.dto.bankbranch.GetBankBranchInfoDTO;
import com.example.deutschebank.entity.CreditAccount;
import com.example.deutschebank.dto.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.GetCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.UpdateCreditAccountDTO;
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
        return modelMapper.map(createDTO, CreditAccount.class);
    }

    public GetCreditAccountDTO convertCreditAccountToGetDTO(CreditAccount creditAccount) {
        return modelMapper.map(creditAccount,
                GetCreditAccountDTO.class);

        /*
        GetCreditAccountDTO getCreditAccountDTO = modelMapper
                .typeMap(CreditAccount.class, GetCreditAccountDTO.class)
                .addMapping(src -> src.getId(), GetCreditAccountDTO::setId)
                .addMapping(src -> src.getClient(),
                        GetCreditAccountDTO::setClient)
                .map(creditAccount);
        return getCreditAccountDTO;

         */
    }

    public List<GetCreditAccountDTO> convertCreditAccountToGetDTOs(List<CreditAccount> creditAccounts) {
        List<GetCreditAccountDTO> getDTOs = new LinkedList<>();
        for (CreditAccount account : creditAccounts) {
            getDTOs.add(modelMapper.map(account,
                    GetCreditAccountDTO.class));
        }
        return getDTOs;
    }

    public CreditAccount convertUpdateDTOToCreditAccount(UpdateCreditAccountDTO updateDTO) {
        return modelMapper.map(updateDTO, CreditAccount.class);
    }
}
