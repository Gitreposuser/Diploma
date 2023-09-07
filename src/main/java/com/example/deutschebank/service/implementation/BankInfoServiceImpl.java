package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.BankInfoDTOConverter;
import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.bankinfo.CreateUpdateBankInfoDTO;
import com.example.deutschebank.model.bankinfo.GetBankInfoDTO;
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

    private final BankInfoDTOConverter bankInfoDTOConverter;

    @Value(value = "${bankInfoService.iban}")
    private String bankIban;

    @Override
    public void createBankInfo(CreateUpdateBankInfoDTO createDTO) {
        if(bankInfoRepository.findAll().isEmpty()) {
            // Get bank IBAN from file
            // To prevent from change
            BankInfo bankInfo =
                    bankInfoDTOConverter.convertCreateUpdateDTOToBankInfo(createDTO);
            bankInfo.setIban(bankIban);
            // Set starting balance as zero
            // All funds should be transferred through transactions
            bankInfo.setBalance(new BigDecimal(0));
            bankInfoRepository.save(bankInfo);
        } else {
            throw new BadOperationException("Can't create another instance of " +
                    "Bank Info");
        }
    }

    @Override
    public GetBankInfoDTO getBankInfo() {
        return bankInfoDTOConverter.convertBankInfoToGetDTO(bankInfoRepository
                .findAll().get(0));
    }

    @Override
    public CreateUpdateBankInfoDTO updateBankInfo(CreateUpdateBankInfoDTO updateDTO) {
        BankInfo bankInfo = bankInfoRepository.findAll().get(0);
        // Remember balance
        BigDecimal balance = bankInfo.getBalance();
        bankInfoRepository.deleteAll();
        // Get bank IBAN from file
        // To prevent from change
        bankInfo =
                bankInfoDTOConverter.convertCreateUpdateDTOToBankInfo(updateDTO);
        bankInfo.setBalance(balance);
        bankInfo.setIban(bankIban);
        bankInfoRepository.save(bankInfo);
        return updateDTO;
    }
}