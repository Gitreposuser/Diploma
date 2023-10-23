package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.GetDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.UpdateDebitAccountDTO;
import com.example.deutschebank.entity.enums.DebitStatus;

import java.util.List;
import java.util.UUID;

public interface DebitAccountService {
    void createDebitAccount(CreateDebitAccountDTO createDTO);

    GetDebitAccountDTO getDebitAccountById(UUID uuid);

    List<GetDebitAccountDTO> getAllDebitAccountByDebitStatus(DebitStatus status);

    List<GetDebitAccountDTO> getAllDebitAccount();

    void updateDebitAccountById(UpdateDebitAccountDTO updateDTO);

    void deleteDebitAccountById(UUID uuid);
}
