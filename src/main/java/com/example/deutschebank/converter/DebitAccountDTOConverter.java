package com.example.deutschebank.converter;

import com.example.deutschebank.dto.debitaccount.*;
import com.example.deutschebank.dto.transaction.CreateTransactionDTO;
import com.example.deutschebank.entity.DebitAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DebitAccountDTOConverter {
    private final ModelMapper modelMapper;

    public DebitAccount convertCreateDTOToDebitAccount(CreateDebitAccountDTO createDTO) {
        return modelMapper.map(createDTO, DebitAccount.class);
    }

    public GetDebitAccountDTO convertDebitAccountToGetDTO(DebitAccount debitAccount) {
        return modelMapper.map(debitAccount, GetDebitAccountDTO.class);
    }

    public List<GetDebitAccountDTO> convertDebitAccountsToGetDTOs(List<DebitAccount> debitAccounts) {
        List<GetDebitAccountDTO> getDTOs = new LinkedList<>();
        for (DebitAccount detail : debitAccounts) {
            getDTOs.add(modelMapper.map(detail,
                    GetDebitAccountDTO.class));
        }
        return getDTOs;
    }

    public DebitAccount convertUpdateDTOToDebitAccount(UpdateDebitAccountDTO updateDTO) {
        return modelMapper.map(updateDTO, DebitAccount.class);
    }

    public CreateTransactionDTO convertTransferDTOToCreateTransactionDTO(
            TransferFundsDTO transferDTO) {
        return modelMapper.map(transferDTO, CreateTransactionDTO.class);
    }
}
