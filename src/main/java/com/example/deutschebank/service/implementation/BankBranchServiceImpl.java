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
import com.example.deutschebank.validators.DtoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankBranchServiceImpl implements BankBranchService {
    private final BankBranchRepository bankBranchRepository;
    private final BankBranchDTOConverter bankBranchDTOConverter;
    private final DtoValidator<CreateBankBranchDTO> dtoValidator;

    @Override
    @Transactional
    public String createBankBranch(CreateBankBranchDTO createDTO) {
        Set<String> violations = dtoValidator.validate(createDTO);
        if(!violations.isEmpty()) {
            return String.join("\n", violations);
        }
        BankBranch bankBranch =
                bankBranchDTOConverter.convertCreateDTOToBankBranch(createDTO);
        bankBranchRepository.save(bankBranch);
        String message = "Bank branch created.";
        log.info(message);
        return message;
    }

    @Override
    @Transactional
    public GetBankBranchDTO getBankBranchById(UUID uuid) {
        validateBankBranchNUllOrNotExist(uuid);
        BankBranch bankBranch = bankBranchRepository.getReferenceById(uuid);
        log.info("Get bank branch DTO with id: " + uuid);
        return bankBranchDTOConverter.convertBankBranchToGetDTO(bankBranch);
    }

    @Override
    @Transactional
    public GetBankBranchDTO getBankBranchByNumber(Integer branchNumber) {
        validateBankBranchNumberNullOrNotExist(branchNumber);
        BankBranch bankBranch =
                bankBranchRepository.getByBranchNumber(branchNumber);
        log.info("Get bank branch DTO by number: " + branchNumber);
        return bankBranchDTOConverter.convertBankBranchToGetDTO(bankBranch);
    }

    @Override
    @Transactional
    public GetBankBranchInfoDTO getBankBranchInfoByNumber(Integer branchNumber) {
        validateBankBranchNumberNullOrNotExist(branchNumber);
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
        validateBankBranchNUllOrNotExist(updateDTO.getId());
        BankBranch bankBranch =
                bankBranchDTOConverter.convertUpdateDTOToBankBranch(updateDTO);
        bankBranchRepository.save(bankBranch);
        log.info("Update bank branch with id: " + bankBranch.getId());
    }

    @Override
    @Transactional
    public void markBankBranchAsDeletedById(UUID uuid) {
        validateBankBranchNUllOrNotExist(uuid);
        BankBranch bankBranch = bankBranchRepository.getReferenceById(uuid);
        bankBranch.setActive(false);
        bankBranchRepository.save(bankBranch);
        log.info("Mark bank branch as deleted with id: " + uuid);
    }

    @Override
    @Transactional
    public void deleteBankBranchById(UUID uuid) {
        validateBankBranchNUllOrNotExist(uuid);
        bankBranchRepository.deleteById(uuid);
        log.warn("Delete bank branch by id: " + uuid);
    }

    @Override
    @Transactional
    public void deleteAllBankBranch() {
        bankBranchRepository.deleteAll();
        log.warn("Delete all bank branch!");
    }

    private void validateBankBranchNumberNullOrNotExist(Integer branchNumber) {
        if (branchNumber == null || !bankBranchRepository
                .existsByBranchNumber(branchNumber)) {
            throw new BadOperationException("Branch doesn't exist!");
        }
    }
/*
    private void validateBankBranchNullOrNotUnique(Integer branchNumber) {
        if (branchNumber == null || bankBranchRepository
                .existsByBranchNumber(branchNumber)) {
            throw new BadOperationException("Branch not unique!");
        }
    }

 */

    private void validateBankBranchNUllOrNotExist(UUID uuid) {
        if (uuid == null || !bankBranchRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}