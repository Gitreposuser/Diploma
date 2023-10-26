package com.example.deutschebank.service.interfaces;
import com.example.deutschebank.dto.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.UpdateBankInfoDTO;

import java.math.BigDecimal;

public interface BankInfoService {
    void createBankInfo(CreateBankInfoDTO createDTO);

    GetBankInfoDTO getBankInfo();

    void updateBankInfo(UpdateBankInfoDTO bankInfoDTO);

    void isNotFundsSufficient(BigDecimal amount);

    void subtractFunds(BigDecimal amount);

    void addFunds(BigDecimal amount);
}