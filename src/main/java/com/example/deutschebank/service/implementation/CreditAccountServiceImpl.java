package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.CreditAccountDTOConverter;
import com.example.deutschebank.entity.CreditAccount;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.GetCreditAccountDTO;
import com.example.deutschebank.dto.creditaccount.UpdateCreditAccountDTO;
import com.example.deutschebank.repository.CreditAccountRepository;
import com.example.deutschebank.service.interfaces.CreditAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
    private final CreditAccountRepository creditAccountRepository;
    private final CreditAccountDTOConverter creditAccountDTOConverter;

    @Override
    @Transactional
    public void createCreditAccount(CreateCreditAccountDTO createDTO) {
        CreditAccount creditAccount =
                creditAccountDTOConverter.convertCreateDTOToCreditAccount(createDTO);
        creditAccountRepository.save(creditAccount);
        log.info("Entity successfully created.");
    }

    @Override
    public GetCreditAccountDTO getCreditAccountById(UUID uuid) {
        checkIfNotExist(uuid);
        CreditAccount creditAccount =
                creditAccountRepository.getReferenceById(uuid);
        return creditAccountDTOConverter.convertCreditAccountToGetDTO(creditAccount);
    }

    @Override
    public List<GetCreditAccountDTO> getAllCreditAccounts() {
        List<CreditAccount> creditAccounts = creditAccountRepository.findAll();
        return creditAccountDTOConverter.convertCreditAccountToGetDTOs(creditAccounts);
    }

    @Override
    public void updateCreditAccountById(UpdateCreditAccountDTO updateDTO) {
        checkIfNotExist(updateDTO.getId());
        CreditAccount creditAccount =
                creditAccountDTOConverter.convertUpdateDTOToCreditAccount(updateDTO);
        creditAccountRepository.save(creditAccount);
        log.info("Entity with id: " + creditAccount.getId() + " is updated.");
    }

    @Override
    public void deleteCreditAccountById(UUID uuid) {
        checkIfNotExist(uuid);
        creditAccountRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkIfNotExist(UUID uuid) {
        if (!creditAccountRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}
