package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.model.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.model.debitaccount.GetDebitAccountDTO;
import com.example.deutschebank.model.debitaccount.UpdateDebitAccountDTO;

import java.util.List;
import java.util.UUID;

public interface DebitAccountService {
    void createDebitAccount(CreateDebitAccountDTO createDTO);

    GetDebitAccountDTO getDebitAccountById(UUID uuid);

    List<GetDebitAccountDTO> getAllDebitAccount();

    void updateDebitAccountById(UpdateDebitAccountDTO updateDTO);

    void deleteDebitAccountById(UUID uuid);
}
