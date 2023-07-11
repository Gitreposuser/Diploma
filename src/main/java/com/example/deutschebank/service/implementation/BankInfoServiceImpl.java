package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.repository.BankInfoRepository;
import com.example.deutschebank.service.interfaces.BankInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankInfoServiceImpl implements BankInfoService {
    private final BankInfoRepository bankInfoRepository;

    @Override
    public void createBankInfo(BankInfo bankInfo) {
        if(bankInfoRepository.findAll().isEmpty()) {
            bankInfoRepository.save(bankInfo);
        }
        throw new BadOperationException("Can't create another instance of " +
                "Bank Info");
    }

    @Override
    public BankInfo getBankInfo() {
        return bankInfoRepository.findAll().get(0);
    }

    @Override
    public BankInfo updateBankInfo(BankInfo bankInfo) {
        bankInfoRepository.deleteAll();
        return bankInfoRepository.save(bankInfo);
    }
}