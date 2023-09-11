package com.example.deutschebank.service.interfaces;
import com.example.deutschebank.model.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.model.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.model.bankinfo.UpdateBankInfoDTO;

public interface BankInfoService {
    void createBankInfo(CreateBankInfoDTO createDTO);

    GetBankInfoDTO getBankInfo();

    UpdateBankInfoDTO updateBankInfo(UpdateBankInfoDTO bankInfoDTO);
}