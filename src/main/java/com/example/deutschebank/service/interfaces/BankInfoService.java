package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.BankInfo;

public interface BankInfoService {
    void createBankInfo(BankInfo bankInfo);

    BankInfo getBankInfo();

    BankInfo updateBankInfo(BankInfo bankInfo);
}
