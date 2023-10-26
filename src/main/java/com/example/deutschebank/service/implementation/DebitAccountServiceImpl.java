package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.DebitAccountDTOConverter;
import com.example.deutschebank.dto.debitaccount.TransferFundsDTO;
import com.example.deutschebank.dto.transaction.CreateTransactionDTO;
import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.enums.DebitStatus;
import com.example.deutschebank.exception.*;
import com.example.deutschebank.dto.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.GetDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.UpdateDebitAccountDTO;
import com.example.deutschebank.repository.DebitAccountRepository;
import com.example.deutschebank.service.interfaces.DebitAccountService;
import com.example.deutschebank.service.interfaces.TransactionService;
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
public class DebitAccountServiceImpl implements DebitAccountService {
    private final DebitAccountRepository debitAccountRepository;
    private final DebitAccountDTOConverter debitAccountDTOConverter;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public void createDebitAccount(CreateDebitAccountDTO createDTO) {
        DebitAccount debitAccount =
                debitAccountDTOConverter.convertCreateDTOToDebitAccount(createDTO);
        debitAccountRepository.save(debitAccount);
        log.info("Create debit account.");
    }

    @Override
    @Transactional
    public GetDebitAccountDTO getDebitAccountById(UUID uuid) {
        checkIfAccountNotExist(uuid);
        DebitAccount debitAccount =
                debitAccountRepository.getReferenceById(uuid);
        log.info("Get debit account by id: " + uuid);
        return debitAccountDTOConverter.convertDebitAccountToGetDTO(debitAccount);
    }

    @Override
    @Transactional
    public List<GetDebitAccountDTO> getAllDebitAccountsByDebitStatus(DebitStatus status) {
        List<DebitAccount> debitAccounts =
                debitAccountRepository.getAllDebitAccountsByDebitStatus(status);
        log.info("Get all debit accounts, status: " + status +
                " quantity: " + debitAccounts.size());
        return debitAccountDTOConverter.convertDebitAccountsToGetDTOs(debitAccounts);
    }

    @Override
    @Transactional
    public List<GetDebitAccountDTO> getAllDebitAccounts() {
        List<DebitAccount> debitAccounts = debitAccountRepository.findAll();
        log.info("Get all debit accounts, quantity: " + debitAccounts.size());
        return debitAccountDTOConverter.convertDebitAccountsToGetDTOs(debitAccounts);
    }

    @Override
    @Transactional
    public void updateDebitAccountById(UpdateDebitAccountDTO updateDTO) {
        checkIfAccountNotExist(updateDTO.getId());
        DebitAccount debitAccount =
                debitAccountDTOConverter.convertUpdateDTOToDebitAccount(updateDTO);
        debitAccountRepository.save(debitAccount);
        log.info("Update debit account id: " + debitAccount.getId());
    }

    @Override
    @Transactional
    public void transferFundsByIban(TransferFundsDTO transferDTO) {
        checkIfNullOrNegativeValue(transferDTO.getAmount());
        checkIfIbanNullOrNotExist(transferDTO.getEmitterIban());
        checkIfIbanNullOrNotExist(transferDTO.getReceiverIban());
        checkIdAccountIsBlocked(transferDTO.getEmitterIban());
        checkIdAccountIsBlocked(transferDTO.getReceiverIban());
        // Check emitter funds
        BigDecimal balance = debitAccountRepository
                .getBalanceByIban(transferDTO.getEmitterIban());
        checkIfNotEnoughFunds(balance, transferDTO.getAmount());
        // Subtract from emitter account
        UUID emitterId = debitAccountRepository.getDebitAccountByIban(transferDTO
                .getEmitterIban());
        subtractFunds(emitterId, transferDTO.getAmount());
        // Add to receiver account
        UUID receiverId = debitAccountRepository.getDebitAccountByIban(transferDTO
                .getReceiverIban());
        addFunds(receiverId, transferDTO.getAmount());
        // Create transaction
        CreateTransactionDTO transactionDTO = debitAccountDTOConverter
                .convertTransferDTOToCreateTransactionDTO(transferDTO);
        transactionService.createTransaction(transactionDTO);
    }

    @Override
    @Transactional
    public void addFunds(UUID uuid, BigDecimal amount) {
        checkIfAccountNotExist(uuid);
        BigDecimal balance =
                debitAccountRepository.findById(uuid).get().getBalance();
        BigDecimal resultBalance = balance.add(amount);
        debitAccountRepository.setBalance(uuid, resultBalance);
    }

    @Override
    @Transactional
    public void subtractFunds(UUID uuid, BigDecimal amount) {
        checkIfAccountNotExist(uuid);
        BigDecimal balance =
                debitAccountRepository.findById(uuid).get().getBalance();
        BigDecimal resultBalance = balance.subtract(amount);
        debitAccountRepository.setBalance(uuid, resultBalance);
    }

    private void checkIdAccountIsBlocked(String iban) {
        DebitStatus status = debitAccountRepository.getDebitStatusByIban(iban);
        if(status == null || status == DebitStatus.BLOCKED) {
            throw new AccountIsBlockedException("Debit account with iban: " + iban +
                    " is blocked!");
        }
    }

    private void checkIfAccountNotExist(UUID uuid) {
        if (uuid == null || (!debitAccountRepository.existsById(uuid))) {
            throw new BadOperationException("Debit account with id: " + uuid +
                    "null or doesn't exist!");
        }
    }

    private void checkIfIbanNullOrNotExist(String iban) {
        if (iban == null || (!debitAccountRepository.existsByIban(iban))) {
            throw new NullOrNotExistException("Debit account with iban: " + iban +
                    "null or doesn't exist!");
        }
    }

    private void checkIfNullOrNegativeValue(BigDecimal value) {
        BigDecimal minValue = new BigDecimal(0);
        if (value == null || (value.compareTo(minValue) < 1)) {
            throw new NullOrNegativeValueException("Amount is null, les or" +
                    " equal to zero!");
        }
    }

    private void checkIfNotEnoughFunds(BigDecimal balance, BigDecimal amount) {
        if (balance == null || amount == null || balance.compareTo(amount) < 1) {
            throw new NotEnoughFundsException("Not enough funds in deposit " +
                    "account!");
        }
    }
}
