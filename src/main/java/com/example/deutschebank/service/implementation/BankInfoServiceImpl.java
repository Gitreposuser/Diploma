package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.BankInfoDTOConverter;
import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.dto.bankinfo.UpdateBankInfoDTO;
import com.example.deutschebank.exception.NotEnoughFundsException;
import com.example.deutschebank.repository.BankInfoRepository;
import com.example.deutschebank.service.interfaces.BankInfoService;
import jakarta.transaction.Transactional;
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

    // One and only id of bank info entity
    private final int id = 1;

    @Value(value = "${bankInfoService.iban}")
    private String bankIban;

    @Override
    @Transactional
    public void createBankInfo(CreateBankInfoDTO createDTO) {
        isExists("Can't create another instance of Bank Info!");
        BankInfo bankInfo =
                bankInfoDTOConverter.convertCreateDTOToBankInfo(createDTO);
        // Manually set id
        bankInfo.setId(id);
        // Get bank IBAN from file
        // To prevent from change
        bankInfo.setIban(bankIban);
        bankInfoRepository.save(bankInfo);
        log.info("Create bank info.");
    }

    @Override
    @Transactional
    public GetBankInfoDTO getBankInfo() {
        isNotExists("Can't receive bank info. Doesnt exist!");
        log.info("Get bank info.");
        return bankInfoDTOConverter.convertBankInfoToGetDTO(bankInfoRepository
                .findById(id).get());
    }

    @Override
    @Transactional
    public void updateBankInfo(UpdateBankInfoDTO updateDTO) {
        isNotExists("Can't update bank info. Doesnt exist!");
        BankInfo bankInfo =
                bankInfoDTOConverter.convertUpdateDTOToBankInfo(updateDTO);
        // Manually set id, IBAN and balance to prevent from changes
        bankInfo.setId(id);
        bankInfo.setIban(bankInfoRepository.findById(id).get().getIban());
        bankInfo.setBalance(bankInfoRepository.findById(id).get().getBalance());
        bankInfoRepository.save(bankInfo);
        log.info("Update bank info.");
    }

    @Override
    public void isNotFundsSufficient(BigDecimal amount) {
        BigDecimal balance = bankInfoRepository.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NotEnoughFundsException("Cannot validate operation, not " +
                    "enough funds!");
        }
    }

    @Override
    public void subtractFunds(BigDecimal amount) {
        BigDecimal balance = bankInfoRepository
                .findById(id).get().getBalance();
        BigDecimal resultBalance = balance.subtract(amount);
        bankInfoRepository.setBalance(resultBalance);
    }

    @Override
    public void addFunds(BigDecimal amount) {
        BigDecimal balance = bankInfoRepository
                .findById(id).get().getBalance();
        BigDecimal resultBalance = balance.add(amount);
        bankInfoRepository.setBalance(resultBalance);
    }

    private void isNotExists(String errorMessage) {
        if (!bankInfoRepository.existsById(id)) {
            throw new BadOperationException(errorMessage);
        }
    }

    private void isExists(String errorMessage) {
        if (bankInfoRepository.existsById(id)) {
            throw new BadOperationException(errorMessage);
        }
    }
}