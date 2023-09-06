package com.example.deutschebank.service.interfaces;
import com.example.deutschebank.model.CreateBankInfoDTO;
import com.example.deutschebank.model.GetBankInfoDTO;
import com.example.deutschebank.model.UpdateBankInfoDTO;

public interface BankInfoService {
    void createBankInfo(CreateBankInfoDTO createDTO);

    GetBankInfoDTO getBankInfo();

    UpdateBankInfoDTO updateBankInfo(UpdateBankInfoDTO bankInfoDTO);
}