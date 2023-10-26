package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.GetDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.TransferFundsDTO;
import com.example.deutschebank.dto.debitaccount.UpdateDebitAccountDTO;
import com.example.deutschebank.entity.enums.DebitStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface DebitAccountService {
    void createDebitAccount(CreateDebitAccountDTO createDTO);

    GetDebitAccountDTO getDebitAccountById(UUID uuid);

    List<GetDebitAccountDTO> getAllDebitAccountsByDebitStatus(DebitStatus status);

    List<GetDebitAccountDTO> getAllDebitAccounts();

    void updateDebitAccountById(UpdateDebitAccountDTO updateDTO);

    void transferFundsByIban(TransferFundsDTO transferDTO);

    void addFunds(UUID uuid, BigDecimal amount);

    void subtractFunds(UUID uuid, BigDecimal amount);
}
