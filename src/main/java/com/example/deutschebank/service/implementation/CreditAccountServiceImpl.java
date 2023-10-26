package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.CreditAccountDTOConverter;
import com.example.deutschebank.dto.creditaccount.GetCreditAccountInfoDTO;
import com.example.deutschebank.dto.transaction.CreateTransactionDTO;
import com.example.deutschebank.entity.CreditAccount;
import com.example.deutschebank.dto.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.GetCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.UpdateCreditAccountDTO;
import com.example.deutschebank.exception.NullOrNotExistException;
import com.example.deutschebank.repository.CreditAccountRepository;
import com.example.deutschebank.repository.DebitAccountRepository;
import com.example.deutschebank.service.interfaces.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
    private final CreditAccountRepository creditAccountRepository;
    private final CreditAccountDTOConverter creditAccountDTOConverter;
    private final BankInfoService bankInfoService;
    private final TransactionService transactionService;
    private final DebitAccountRepository debitAccountRepository;
    private final ClientService clientService;

    @Override
    @Transactional
    public void createCreditAccount(CreateCreditAccountDTO createDTO) {
        UUID clientId = createDTO.getClient().getId();
        validateCreditAccountIdNullOrNotExist(clientId);
        String clientIban =
                clientService.getClientById(clientId).getDebitAccount().getIban();
        UUID debitAccountId =
                clientService.getClientById(clientId).getDebitAccount().getId();

        bankInfoService.isNotFundsSufficient(createDTO.getDebt());
        bankInfoService.subtractFunds(createDTO.getDebt());
        addFunds(debitAccountId, createDTO.getDebt());

        CreateTransactionDTO transactionDTO = new CreateTransactionDTO();
        transactionDTO.setAmount(createDTO.getDebt());
        transactionDTO.setEmitterIban(bankInfoService.getBankInfo().getIban());
        transactionDTO.setReceiverIban(clientIban);

        transactionService.createTransaction(transactionDTO);
        CreditAccount creditAccount =
                creditAccountDTOConverter.convertCreateDTOToCreditAccount(createDTO);
        creditAccountRepository.save(creditAccount);
        log.info("Create credit account.");
    }

    @Override
    @Transactional
    public GetCreditAccountDTO getCreditAccountById(UUID uuid) {
        validateCreditAccountIdNullOrNotExist(uuid);
        CreditAccount creditAccount =
                creditAccountRepository.getReferenceById(uuid);
        log.info("Get credit account by id: " + uuid);
        return creditAccountDTOConverter.convertCreditAccountToGetDTO(creditAccount);
    }

    @Override
    @Transactional
    public List<GetCreditAccountInfoDTO> getCreditAccountsInfoByClientFullName(String fullName) {
        List<CreditAccount> creditAccounts =
                creditAccountRepository.getCreditAccountsByFullName(fullName);
        log.info("Get credit accounts by client full name: " + fullName);
        return creditAccountDTOConverter.convertCreditAccountsToGetInfoDTOs(creditAccounts);
    }

    @Override
    @Transactional
    public List<GetCreditAccountDTO> getAllActiveCreditAccounts() {
        List<CreditAccount> creditAccounts = creditAccountRepository
                .getAllActiveCreditAccounts();
        log.info("Get all active credit accounts, quantity: " + creditAccounts.size());
        return creditAccountDTOConverter.convertCreditAccountToGetDTOs(creditAccounts);
    }

    @Override
    @Transactional
    public List<GetCreditAccountDTO> getAllCreditAccounts() {
        List<CreditAccount> creditAccounts = creditAccountRepository.findAll();
        log.info("Get all credit accounts, quantity: " + creditAccounts.size());
        return creditAccountDTOConverter.convertCreditAccountToGetDTOs(creditAccounts);
    }

    @Override
    @Transactional
    public void updateCreditAccountById(UpdateCreditAccountDTO updateDTO) {
        validateCreditAccountIdNullOrNotExist(updateDTO.getId());
        CreditAccount creditAccount =
                creditAccountDTOConverter.convertUpdateDTOToCreditAccount(updateDTO);
        creditAccountRepository.save(creditAccount);
        log.info("Update credit account by id: " + updateDTO.getId());
    }

    @Override
    @Transactional
    public void deleteCreditAccountById(UUID uuid) {
        validateCreditAccountIdNullOrNotExist(uuid);
        creditAccountRepository.deleteById(uuid);
        log.warn("Delete credit account by id: " + uuid);
    }

    @Override
    @Transactional
    public void addDebt(UUID uuid, BigDecimal amount) {
        validateCreditAccountIdNullOrNotExist(uuid);
        BigDecimal debt =
                creditAccountRepository.getDebtById(uuid);
        BigDecimal resultDebt = debt.add(amount);
        creditAccountRepository.setDept(uuid, resultDebt);
    }

    @Override
    @Transactional
    public void subtractDebt(UUID uuid, BigDecimal amount) {
        validateCreditAccountIdNullOrNotExist(uuid);
        BigDecimal debt =
                creditAccountRepository.getDebtById(uuid);
        BigDecimal resultDebt = debt.subtract(amount);
        creditAccountRepository.setDept(uuid, resultDebt);
    }

    private void addFunds(UUID uuid, BigDecimal amount) {
        validateDebitAccountNullOrNotExist(uuid);
        BigDecimal balance =
                debitAccountRepository.findById(uuid).get().getBalance();
        BigDecimal resultBalance = balance.add(amount);
        debitAccountRepository.setBalance(uuid, resultBalance);
    }

    private void validateCreditAccountIdNullOrNotExist(UUID uuid) {
        if (uuid == null || !creditAccountRepository.existsById(uuid)) {
            throw new NullOrNotExistException("Credit account with id: " + uuid +
                    " is null or doesn't exist!");
        }
    }

    private void validateDebitAccountNullOrNotExist(UUID uuid) {
        if (uuid == null || !debitAccountRepository.existsById(uuid)) {
            throw new NullOrNotExistException("Debit account with id: " + uuid +
                    " is null or doesn't exist!");
        }
    }
}
