package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.DebitAccountDTOConverter;
import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.entity.enums.DebitStatus;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.GetDebitAccountDTO;
import com.example.deutschebank.dto.debitaccount.UpdateDebitAccountDTO;
import com.example.deutschebank.repository.DebitAccountRepository;
import com.example.deutschebank.service.interfaces.DebitAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DebitAccountServiceImpl implements DebitAccountService {
    private final DebitAccountRepository debitAccountRepository;
    private final DebitAccountDTOConverter debitAccountDTOConverter;

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
        checkIfNotExist(uuid);
        DebitAccount debitAccount =
                debitAccountRepository.getReferenceById(uuid);
        log.info("Get debit account by id: " + uuid);
        return debitAccountDTOConverter.convertDebitAccountToGetDTO(debitAccount);
    }

    @Override
    @Transactional
    public List<GetDebitAccountDTO> getAllDebitAccountByDebitStatus(DebitStatus status) {
        List<DebitAccount> debitAccounts =
                debitAccountRepository.getAllDebitAccountsByDebitStatus(status);
        log.info("Get all debit accounts, status: " + status +
                " quantity: " + debitAccounts.size());
        return debitAccountDTOConverter.convertDebitAccountsToGetDTOs(debitAccounts);
    }

    @Override
    @Transactional
    public List<GetDebitAccountDTO> getAllDebitAccount() {
        List<DebitAccount> debitAccounts = debitAccountRepository.findAll();
        log.info("Get all debit accounts, quantity: " + debitAccounts.size());
        return debitAccountDTOConverter.convertDebitAccountsToGetDTOs(debitAccounts);
    }

    @Override
    @Transactional
    public void updateDebitAccountById(UpdateDebitAccountDTO updateDTO) {
        checkIfNotExist(updateDTO.getId());
        DebitAccount debitAccount =
                debitAccountDTOConverter.convertUpdateDTOToDebitAccount(updateDTO);
        debitAccountRepository.save(debitAccount);
        log.info("Update debit account id: " + debitAccount.getId());
    }

    @Override
    @Transactional
    public void deleteDebitAccountById(UUID uuid) {
        checkIfNotExist(uuid);
        debitAccountRepository.deleteById(uuid);
        log.info("Delete debit account id: " + uuid);
    }

    private void checkIfNotExist(UUID uuid) {
        if (!debitAccountRepository.existsById(uuid)) {
            throw new BadOperationException("Debit account with id: " + uuid +
                    "doesn't exist!");
        }
    }
}
