package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.BankInfoDTOConverter;
import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.model.bankinfo.GetBankInfoDTO;
import com.example.deutschebank.model.bankinfo.UpdateBankInfoDTO;
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
        checkIfExists("Can't create another instance of Bank Info!");
        BankInfo bankInfo =
                bankInfoDTOConverter.convertCreateDTOToBankInfo(createDTO);
        // Manually set id
        bankInfo.setId(id);
        // Get bank IBAN from file
        // To prevent from change
        bankInfo.setIban(bankIban);
        // Set starting balance as zero
        // All funds should be transferred through transactions
        bankInfo.setBalance(new BigDecimal(0));
        bankInfoRepository.save(bankInfo);
    }

    @Override
    public GetBankInfoDTO getBankInfo() {
        checkIfNotExists("Can't receive bank info. Doesnt exist!");
        return bankInfoDTOConverter.convertBankInfoToGetDTO(bankInfoRepository
                .findById(id).get());
    }

    @Override
    @Transactional
    public UpdateBankInfoDTO updateBankInfo(UpdateBankInfoDTO updateDTO) {
        checkIfNotExists("Can't update bank info. Doesnt exist!");

        BankInfo bankInfo =
                bankInfoDTOConverter.convertUpdateDTOToBankInfo(updateDTO);
        // Manually set id, IBAN and balance to prevent from changes
        bankInfo.setId(id);
        bankInfo.setIban(bankInfoRepository.findById(id).get().getIban());
        bankInfo.setBalance(bankInfoRepository.findById(id).get().getBalance());
        bankInfoRepository.save(bankInfo);
        return updateDTO;
    }

    private void checkIfNotExists(String errorMessage) {
        if(!bankInfoRepository.existsById(id)) {
            throw new BadOperationException(errorMessage);
        }
    }

    private void checkIfExists(String errorMessage) {
        if(bankInfoRepository.existsById(id)) {
            throw new BadOperationException(errorMessage);
        }
    }
}