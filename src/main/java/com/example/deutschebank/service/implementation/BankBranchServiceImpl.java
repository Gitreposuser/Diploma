package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.BankBranchDTOConverter;
import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.model.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.model.bankbranch.UpdateBankBranchDTO;
import com.example.deutschebank.model.location.GetLocationDTO;
import com.example.deutschebank.repository.BankBranchRepository;
import com.example.deutschebank.service.interfaces.BankBranchService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankBranchServiceImpl implements BankBranchService {
    private final BankBranchRepository bankBranchRepository;
    private final BankBranchDTOConverter bankBranchDTOConverter;

    @Override
    @Transactional
    public void createBankBranch(CreateBankBranchDTO createDTO) {
        checkIfBranchNotUnique(createDTO.getBranchNumber());
        BankBranch bankBranch =
                bankBranchDTOConverter.convertCreateDTOToBankBranch(createDTO);
        bankBranchRepository.save(bankBranch);
        log.info("Entity successfully created.");
    }

    @Override
    public GetBankBranchDTO getBankBranchById(UUID uuid) {
        checkIfNotExist(uuid);
        BankBranch bankBranch = bankBranchRepository.getReferenceById(uuid);
        return bankBranchDTOConverter.convertBankBranchToGetDTO(bankBranch);
    }

    @Override
    public List<GetBankBranchDTO> getAllBankBranches() {
        List<BankBranch> bankBranches = bankBranchRepository.findAll();
        return bankBranchDTOConverter.convertBankBranchesToGetDTOs(bankBranches);
    }

    @Override
    @Transactional
    public void updateBankBranchById(UpdateBankBranchDTO updateDTO) {
        checkIfNotExist(updateDTO.getId());
        checkIfBranchNotUnique(updateDTO.getBranchNumber());
        BankBranch bankBranch =
                bankBranchDTOConverter.convertUpdateDTOToBankBranch(updateDTO);
        bankBranchRepository.save(bankBranch);
        log.info("Entity with id: " + bankBranch.getId() + " is updated.");
    }

    @Override
    public void deleteBankBranchById(UUID uuid) {
        checkIfNotExist(uuid);
        bankBranchRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkIfBranchNotUnique(Integer branchNumber) {
        if(bankBranchRepository.existsByBranchNumber(branchNumber)) {
            throw new BadOperationException("Branch not unique!");
        }
    }

    private void checkIfNotExist(UUID uuid) {
        if(!bankBranchRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}