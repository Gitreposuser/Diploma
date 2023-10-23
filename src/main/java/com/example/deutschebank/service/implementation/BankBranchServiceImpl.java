package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.BankBranchDTOConverter;
import com.example.deutschebank.dto.bankbranch.GetBankBranchInfoDTO;
import com.example.deutschebank.entity.BankBranch;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.UpdateBankBranchDTO;
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
        isBranchNotUnique(createDTO.getBranchNumber());
        BankBranch bankBranch =
                bankBranchDTOConverter.convertCreateDTOToBankBranch(createDTO);
        bankBranchRepository.save(bankBranch);
        log.info("Entity successfully created.");
    }

    @Override
    @Transactional
    public GetBankBranchDTO getBankBranchById(UUID uuid) {
        isNotExist(uuid);
        BankBranch bankBranch = bankBranchRepository.getReferenceById(uuid);
        log.info("Get bank branch DTO with id: " + uuid);
        return bankBranchDTOConverter.convertBankBranchToGetDTO(bankBranch);
    }

    @Override
    @Transactional
    public GetBankBranchDTO getBankBranchByNumber(Integer branchNumber) {
        isBranchNumberNotExist(branchNumber);
        BankBranch bankBranch =
                bankBranchRepository.getByBranchNumber(branchNumber);
        log.info("Get bank branch DTO by number: " + branchNumber);
        return bankBranchDTOConverter.convertBankBranchToGetDTO(bankBranch);
    }

    @Override
    @Transactional
    public GetBankBranchInfoDTO getBankBranchInfoByNumber(Integer branchNumber) {
        isBranchNumberNotExist(branchNumber);
        BankBranch bankBranch =
                bankBranchRepository.getByBranchNumber(branchNumber);
        log.info("Get bank branch info DTO by number: " + branchNumber);
        return bankBranchDTOConverter.convertBankBranchToGetInfoDTO(bankBranch);
    }

    @Override
    @Transactional
    public List<GetBankBranchDTO> getAllActiveBankBranches() {
        List<BankBranch> bankBranches = bankBranchRepository.getAllActiveBankBranches();
        log.info("Get all bank branch DTO, quantity: " + bankBranches.size());
        return bankBranchDTOConverter.convertBankBranchesToGetDTOs(bankBranches);
    }

    @Override
    @Transactional
    public List<GetBankBranchDTO> getAllBankBranches() {
        List<BankBranch> bankBranches = bankBranchRepository.findAll();
        log.info("Get all bank branch DTO, quantity: " + bankBranches.size());
        return bankBranchDTOConverter.convertBankBranchesToGetDTOs(bankBranches);
    }

    @Override
    @Transactional
    public void updateBankBranchById(UpdateBankBranchDTO updateDTO) {
        isNotExist(updateDTO.getId());
        BankBranch bankBranch =
                bankBranchDTOConverter.convertUpdateDTOToBankBranch(updateDTO);
        bankBranch.getLocation().getId();
        bankBranchRepository.save(bankBranch);
        log.info("Entity with id: " + bankBranch.getId() + " is updated.");
    }

    @Override
    @Transactional
    public void markBankBranchAsDeletedById(UUID uuid) {
        isNotExist(uuid);
        BankBranch bankBranch = bankBranchRepository.getReferenceById(uuid);
        bankBranch.setActive(false);
        bankBranchRepository.save(bankBranch);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void isBranchNumberNotExist(Integer branchNumber) {
        if (!bankBranchRepository.existsByBranchNumber(branchNumber)) {
            throw new BadOperationException("Branch doesn't exist!");
        }
    }

    private void isBranchNotUnique(Integer branchNumber) {
        if (bankBranchRepository.existsByBranchNumber(branchNumber)) {
            throw new BadOperationException("Branch not unique!");
        }
    }

    private void isNotExist(UUID uuid) {
        if (!bankBranchRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}