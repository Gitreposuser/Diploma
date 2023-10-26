package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.DebitAccountDTOConverter;
import com.example.deutschebank.dto.debitaccount.*;
import com.example.deutschebank.dto.transaction.CreateTransactionDTO;
import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.enums.DebitStatus;
import com.example.deutschebank.exception.*;
import com.example.deutschebank.repository.CreditAccountRepository;
import com.example.deutschebank.repository.DebitAccountRepository;
import com.example.deutschebank.service.interfaces.BankInfoService;
import com.example.deutschebank.service.interfaces.CreditAccountService;
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
    private final BankInfoService bankInfoService;
    private final CreditAccountRepository creditAccountRepository;

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
        validateDebitAccountNullOrNotExist(uuid);
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
        validateDebitAccountNullOrNotExist(updateDTO.getId());
        DebitAccount debitAccount =
                debitAccountDTOConverter.convertUpdateDTOToDebitAccount(updateDTO);
        debitAccountRepository.save(debitAccount);
        log.info("Update debit account id: " + debitAccount.getId());
    }

    @Override
    @Transactional
    public void transferFundsByIban(TransferFundsDTO transferDTO) {
        validateValueNullOrNegative(transferDTO.getAmount());
        validateDebitAccountNullOrNotExist(transferDTO.getEmitterIban());
        validateDebitAccountNullOrNotExist(transferDTO.getReceiverIban());
        validateDebitAccountIsBlocked(transferDTO.getEmitterIban());
        validateDebitAccountIsBlocked(transferDTO.getReceiverIban());
        // Check emitter funds
        validateNotEnoughFunds(transferDTO.getEmitterIban(), transferDTO.getAmount());
        // Subtract from emitter account
        UUID emitterId = debitAccountRepository
                .getDebitAccountIdByIban(transferDTO.getEmitterIban());
        subtractFunds(emitterId, transferDTO.getAmount());
        // Add to receiver account
        UUID receiverId = debitAccountRepository
                .getDebitAccountIdByIban(transferDTO.getReceiverIban());
        addFunds(receiverId, transferDTO.getAmount());
        // Create transaction
        CreateTransactionDTO transactionDTO = debitAccountDTOConverter
                .convertTransferDTOToCreateTransactionDTO(transferDTO);
        transactionService.createTransaction(transactionDTO);
        log.info("Transfer funds, amount: " + transferDTO.getAmount());
    }

    @Override
    @Transactional
    public void payDebt(PayDeptDTO payDeptDTO) {
        UUID debitAccountId = payDeptDTO.getDebitAccountId();
        validateDebitAccountNullOrNotExist(debitAccountId);
        validateDebitAccountIsBlocked(debitAccountId);

        BigDecimal balance = debitAccountRepository
                .getReferenceById(debitAccountId).getBalance();
        validateValueNullOrNegative(balance);

        UUID creditAccountId = payDeptDTO.getCreditAccountId();
        validateCreditAccountNullOrNotExist(creditAccountId);
        BigDecimal debt = creditAccountRepository.getDebtById(creditAccountId);
        BigDecimal amount = payDeptDTO.getAmount();

        // Check emitter funds
        validateNotEnoughFunds(balance, amount);
        // Transfer amount is bigger than debt?
        BigDecimal transferAmount = null;
        log.warn("balance: " + balance + " amount: " + amount);
        if (debt.compareTo(amount) == 1) {
            transferAmount = amount;
            log.warn("Amount less than debt.");
        }
        if (debt.compareTo(amount) == 0) {
            transferAmount = amount;
            creditAccountRepository.closeCredit(creditAccountId);
            log.warn("Amount equal debt.");
        }
        if (debt.compareTo(amount) == -1) {
            transferAmount = debt;
            creditAccountRepository.closeCredit(creditAccountId);
            log.warn("Amount greater than debt.");
        }

        log.warn("Transfer amount: " + transferAmount);
        subtractFunds(debitAccountId, transferAmount);
        subtractDebt(creditAccountId, transferAmount);
        bankInfoService.addFunds(transferAmount);

        String emitterIban =
                debitAccountRepository.getReferenceById(debitAccountId).getIban();
        String receiverIban = bankInfoService.getBankInfo().getIban();
        CreateTransactionDTO transactionDTO = new CreateTransactionDTO();
        transactionDTO.setEmitterIban(emitterIban);
        transactionDTO.setReceiverIban(receiverIban);
        transactionDTO.setAmount(transferAmount);
        transactionService.createTransaction(transactionDTO);
        log.info("Pay debt, amount: " + payDeptDTO.getAmount());
    }

    @Override
    @Transactional
    public void addFunds(UUID uuid, BigDecimal amount) {
        validateDebitAccountNullOrNotExist(uuid);
        BigDecimal balance =
                debitAccountRepository.findById(uuid).get().getBalance();
        BigDecimal resultBalance = balance.add(amount);
        debitAccountRepository.setBalance(uuid, resultBalance);
    }

    @Override
    @Transactional
    public void subtractFunds(UUID uuid, BigDecimal amount) {
        validateDebitAccountNullOrNotExist(uuid);
        BigDecimal balance =
                debitAccountRepository.findById(uuid).get().getBalance();
        BigDecimal resultBalance = balance.subtract(amount);
        debitAccountRepository.setBalance(uuid, resultBalance);
    }

    private void subtractDebt(UUID uuid, BigDecimal amount) {
        validateCreditAccountNullOrNotExist(uuid);
        BigDecimal debt = creditAccountRepository.getDebtById(uuid);
        BigDecimal resultDebt = debt.subtract(amount);
        creditAccountRepository.setDept(uuid, resultDebt);
    }

    private void validateDebitAccountIsBlocked(String iban) {
        DebitStatus status = debitAccountRepository.getDebitStatusByIban(iban);
        if (status == null || status == DebitStatus.BLOCKED) {
            throw new AccountIsBlockedException("Debit account with iban: " + iban +
                    " is blocked!");
        }
    }

    private void validateDebitAccountIsBlocked(UUID uuid) {
        DebitStatus status = debitAccountRepository
                .getReferenceById(uuid).getDebitStatus();
        if(status == null || status == DebitStatus.BLOCKED) {
            throw new AccountIsBlockedException("Debit account with id: " + uuid +
                    " is blocked!");
        }
    }

    private void validateDebitAccountNullOrNotExist(UUID uuid) {
        if (uuid == null || (!debitAccountRepository.existsById(uuid))) {
            throw new BadOperationException("Debit account with id: " + uuid +
                    "null or doesn't exist!");
        }
    }

    private void validateDebitAccountNullOrNotExist(String iban) {
        if (iban == null || (!debitAccountRepository.existsByIban(iban))) {
            throw new NullOrNotExistException("Debit account with iban: " + iban +
                    "null or doesn't exist!");
        }
    }

    private void validateValueNullOrNegative(BigDecimal value) {
        BigDecimal minValue = new BigDecimal(0);
        if (value == null || (value.compareTo(minValue) < 1)) {
            throw new NullOrNegativeValueException("Amount is null, les or" +
                    " equal to zero!");
        }
    }

    private void validateCreditAccountNullOrNotExist(UUID uuid) {
        if (uuid == null || (!creditAccountRepository.existsById(uuid))) {
            throw new NullOrNotExistException("Credit account doesn't exist!");
        }
    }

    private void validateNotEnoughFunds(BigDecimal balance, BigDecimal amount) {
        if (balance == null || amount == null || balance.compareTo(amount) ==
                -1) {
            throw new NotEnoughFundsException("Not enough funds in deposit " +
                    "account!");
        }
    }

    private void validateNotEnoughFunds(String iban, BigDecimal amount) {
        BigDecimal balance = debitAccountRepository.getBalanceByIban(iban);
        validateNotEnoughFunds(balance, amount);
    }
}
