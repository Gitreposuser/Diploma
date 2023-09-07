package com.example.deutschebank.service.interfaces;
import com.example.deutschebank.model.bankinfo.CreateUpdateBankInfoDTO;
import com.example.deutschebank.model.bankinfo.GetBankInfoDTO;

public interface BankInfoService {
    void createBankInfo(CreateUpdateBankInfoDTO createDTO);

    GetBankInfoDTO getBankInfo();

    CreateUpdateBankInfoDTO updateBankInfo(CreateUpdateBankInfoDTO bankInfoDTO);
}