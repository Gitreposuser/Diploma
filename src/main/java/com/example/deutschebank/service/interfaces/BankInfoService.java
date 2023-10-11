package com.example.deutschebank.service.interfaces;
import com.example.deutschebank.dto.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.UpdateBankInfoDTO;

public interface BankInfoService {
    void createBankInfo(CreateBankInfoDTO createDTO);

    GetBankInfoDTO getBankInfo();

    UpdateBankInfoDTO updateBankInfo(UpdateBankInfoDTO bankInfoDTO);
}