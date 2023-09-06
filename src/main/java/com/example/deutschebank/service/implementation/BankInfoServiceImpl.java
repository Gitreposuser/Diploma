package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.mapper.CreateBankInfoMapper;
import com.example.deutschebank.mapper.GetBankInfoMapper;
import com.example.deutschebank.mapper.UpdateBankInfoMapper;
import com.example.deutschebank.model.CreateBankInfoDTO;
import com.example.deutschebank.model.GetBankInfoDTO;
import com.example.deutschebank.model.UpdateBankInfoDTO;
import com.example.deutschebank.repository.BankInfoRepository;
import com.example.deutschebank.service.interfaces.BankInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankInfoServiceImpl implements BankInfoService {
    private final BankInfoRepository bankInfoRepository;
    private final CreateBankInfoMapper createMapper;
    private final GetBankInfoMapper getMapper;
    private final UpdateBankInfoMapper updateMapper;

    @Value(value = "${bankInfoService.iban}")
    private String bankIban;

    @Override
    public void createBankInfo(CreateBankInfoDTO createDTO) {
        if(bankInfoRepository.findAll().isEmpty()) {
            // Get bank IBAN from file
            // To prevent from change
            BankInfo bankInfo = createMapper.map(createDTO);
            bankInfo.setIban(bankIban);
            bankInfoRepository.save(bankInfo);
        } else {
            throw new BadOperationException("Can't create another instance of " +
                    "Bank Info");
        }
    }

    @Override
    public GetBankInfoDTO getBankInfo() {
        return getMapper.map(bankInfoRepository.findAll().get(0));
    }

    @Override
    public UpdateBankInfoDTO updateBankInfo(UpdateBankInfoDTO updateDTO) {
        BankInfo bankInfo = bankInfoRepository.findAll().get(0);
        // Remember balance
        BigDecimal balance = bankInfo.getBalance();
        bankInfoRepository.deleteAll();
        // Get bank IBAN from file
        // To prevent from change
        bankInfo = updateMapper.map(updateDTO);
        bankInfo.setBalance(balance);
        bankInfo.setIban(bankIban);
        bankInfoRepository.save(bankInfo);
        return updateDTO;
    }
}