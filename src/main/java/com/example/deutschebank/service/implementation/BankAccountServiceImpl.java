package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.BankInfoDTOConverter;
import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.entity.BankAccount;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.bankaccount.CreateBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.GetBankAccountDTO;
import com.example.deutschebank.dto.bankaccount.UpdateBankAccountDTO;
import com.example.deutschebank.exception.NotEnoughFundsException;
import com.example.deutschebank.repository.BankAccountRepository;
import com.example.deutschebank.service.interfaces.BankAccountService;
import com.example.deutschebank.validators.DtoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    private final BankInfoDTOConverter bankInfoDTOConverter;

    private final DtoValidator<CreateBankBranchDTO> dtoValidator;

    // One and only id of bank info entity
    @Value(value = "${bankInfoService.id}")
    private final int bankId = 1;

    @Value(value = "${bankInfoService.iban}")
    private String bankIban;

    @Override
    @Transactional
    public void createBankInfo(CreateBankAccountDTO createDTO) {
        validateBankInfoExists("Can't create another instance of Bank Info!");
        BankAccount bankAccount =
                bankInfoDTOConverter.convertCreateDTOToBankInfo(createDTO);
        // Manually set id
        bankAccount.setId(bankId);
        // Get bank IBAN from file
        // To prevent from change
        bankAccount.setIban(bankIban);
        bankAccountRepository.save(bankAccount);
        log.info("Create bank info.");
    }

    @Override
    @Transactional
    public GetBankAccountDTO getBankInfo() {
        validateBankInfoNotExists("Can't receive bank info. Doesnt exist!");
        log.info("Get bank info.");
        return bankInfoDTOConverter.convertBankInfoToGetDTO(bankAccountRepository
                .findById(bankId).get());
    }

    @Override
    @Transactional
    public void updateBankInfo(UpdateBankAccountDTO updateDTO) {
        validateBankInfoNotExists("Can't update bank info. Doesnt exist!");
        BankAccount bankAccount =
                bankInfoDTOConverter.convertUpdateDTOToBankInfo(updateDTO);
        // Manually set id, IBAN and balance to prevent from changes
        bankAccount.setId(bankId);
        bankAccount.setIban(bankAccountRepository.findById(bankId).get().getIban());
        bankAccount.setBalance(bankAccountRepository.findById(bankId).get().getBalance());
        bankAccountRepository.save(bankAccount);
        log.info("Update bank info.");
    }

    @Override
    public void isNotFundsSufficient(BigDecimal amount) {
        BigDecimal balance = bankAccountRepository.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NotEnoughFundsException("Cannot validate operation, not " +
                    "enough funds!");
        }
    }

    @Override
    public void subtractFunds(BigDecimal amount) {
        BigDecimal balance = bankAccountRepository
                .findById(bankId).get().getBalance();
        BigDecimal resultBalance = balance.subtract(amount);
        bankAccountRepository.setBalance(resultBalance);
    }

    @Override
    public void addFunds(BigDecimal amount) {
        BigDecimal balance = bankAccountRepository
                .findById(bankId).get().getBalance();
        BigDecimal resultBalance = balance.add(amount);
        bankAccountRepository.setBalance(resultBalance);
    }

    private void validateBankInfoNotExists(String errorMessage) {
        if (!bankAccountRepository.existsById(bankId)) {
            throw new BadOperationException(errorMessage);
        }
    }

    private void validateBankInfoExists(String errorMessage) {
        if (bankAccountRepository.existsById(bankId)) {
            throw new BadOperationException(errorMessage);
        }
    }
}