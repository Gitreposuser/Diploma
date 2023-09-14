package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.DebitAccountDTOConverter;
import com.example.deutschebank.entity.DebitAccount;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.model.debitaccount.GetDebitAccountDTO;
import com.example.deutschebank.model.debitaccount.UpdateDebitAccountDTO;
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
        log.info("Entity successfully created.");
    }

    @Override
    public GetDebitAccountDTO getDebitAccountById(UUID uuid) {
        checkIfNotExist(uuid);
        DebitAccount debitAccount =
                debitAccountRepository.getReferenceById(uuid);
        return debitAccountDTOConverter.convertDebitAccountToGetDTO(debitAccount);
    }

    @Override
    public List<GetDebitAccountDTO> getAllDebitAccount() {
        List<DebitAccount> debitAccounts = debitAccountRepository.findAll();
        return debitAccountDTOConverter.convertDebitAccountsToGetDTOs(debitAccounts);
    }

    @Override
    @Transactional
    public void updateDebitAccountById(UpdateDebitAccountDTO updateDTO) {
        checkIfNotExist(updateDTO.id);
        DebitAccount debitAccount =
                debitAccountDTOConverter.convertUpdateDTOToDebitAccount(updateDTO);
        debitAccountRepository.save(debitAccount);
        log.info("Entity with id: " + debitAccount.getId() + " is updated.");
    }

    @Override
    public void deleteDebitAccountById(UUID uuid) {
        checkIfNotExist(uuid);
        debitAccountRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkIfNotExist(UUID uuid) {
        if (!debitAccountRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}
