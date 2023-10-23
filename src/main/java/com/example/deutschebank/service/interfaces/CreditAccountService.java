package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.GetCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.UpdateCreditAccountDTO;

import java.util.List;
import java.util.UUID;

public interface CreditAccountService {
    void createCreditAccount(CreateCreditAccountDTO createDTO);

    GetCreditAccountDTO getCreditAccountById(UUID uuid);

    List<GetCreditAccountDTO> getAllCreditAccounts();

    void updateCreditAccountById(UpdateCreditAccountDTO updateDTO);

    void deleteCreditAccountById(UUID uuid);
}
