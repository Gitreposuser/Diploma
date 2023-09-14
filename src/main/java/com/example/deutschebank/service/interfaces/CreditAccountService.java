package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.model.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.model.creditaccount.GetCreditAccountDTO;
import com.example.deutschebank.model.creditaccount.UpdateCreditAccountDTO;

import java.util.List;
import java.util.UUID;

public interface CreditAccountService {
    void createCreditAccount(CreateCreditAccountDTO createDTO);
    GetCreditAccountDTO getCreditAccountById(UUID uuid);

    List<GetCreditAccountDTO> getAllCreditAccounts();

    void updateCreditAccountById(UpdateCreditAccountDTO updateDTO);
    void deleteCreditAccountById(UUID uuid);
}
