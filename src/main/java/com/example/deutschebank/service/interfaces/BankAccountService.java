package com.example.deutschebank.service.interfaces;
import com.example.deutschebank.dto.bankaccount.CreateBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.GetBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.UpdateBankAccountDTO;

import java.math.BigDecimal;

public interface BankAccountService {
    void createBankInfo(CreateBankAccountDTO createDTO);

    GetBankAccountDTO getBankInfo();

    void updateBankInfo(UpdateBankAccountDTO bankInfoDTO);

    void isNotFundsSufficient(BigDecimal amount);

    void subtractFunds(BigDecimal amount);

    void addFunds(BigDecimal amount);
}