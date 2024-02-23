package com.example.deutschebank.controller;

import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchDTO;
import com.example.deutschebank.dto.bankbranch.GetBankBranchInfoDTO;
import com.example.deutschebank.dto.bankbranch.UpdateBankBranchDTO;
import com.example.deutschebank.service.interfaces.BankBranchService;
//import com.example.deutschebank.validators.DtoValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bank-branch")
public class BankBranchController {
    private final BankBranchService bankBranchService;
    //private final DtoValidator validator;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createBankBranch(
            @RequestBody @Valid CreateBankBranchDTO createDTO) {
        /*
        Set<String> errors = validator.validate(createDTO);
        if(!errors.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String str : errors) {
                builder.append(str).append("\n");
            }
            return ResponseEntity.ok().body(builder.toString());
            //return errors.stream().collect(Collectors.joining(","));
        }

         */
        return ResponseEntity.ok().body(bankBranchService
                .createBankBranch(createDTO));
    }

    @GetMapping(value = "/get/by/id/{uuid}")
    public ResponseEntity<GetBankBranchDTO> getBankBranchById(
            @PathVariable("uuid") UUID uuid) {
        GetBankBranchDTO getDTO = bankBranchService.getBankBranchById(uuid);
        return ResponseEntity.ok().body(getDTO);
    }

    @GetMapping(value = "/get/by/branch-number/{branch_number}")
    public ResponseEntity<GetBankBranchDTO> getBankBranchByBranchNumber
            (@PathVariable("branch_number") Integer branch_number) {
        GetBankBranchDTO getDTO =
                bankBranchService.getBankBranchByNumber(branch_number);
        return ResponseEntity.ok().body(getDTO);
    }

    @GetMapping(value = "/get/info/by/branch-number/{branch_number}")
    public ResponseEntity<GetBankBranchInfoDTO> getBankBranchInfoByBranchNumber
            (@PathVariable("branch_number") Integer branch_number) {
        GetBankBranchInfoDTO getDTO =
                bankBranchService.getBankBranchInfoByNumber(branch_number);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all/active")
    public ResponseEntity<List<GetBankBranchDTO>> getAllActiveBankBranches() {
        List<GetBankBranchDTO> getAllDTOs =
                bankBranchService.getAllActiveBankBranches();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetBankBranchDTO>> getAllBankBranches() {
        List<GetBankBranchDTO> getAllDTOs =
                bankBranchService.getAllBankBranches();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by/id")
    public void updateBankBranchById(@RequestBody @Valid UpdateBankBranchDTO updateDTO) {
        bankBranchService.updateBankBranchById(updateDTO);
    }

    @DeleteMapping(value = "/mark-as-deleted/by/id/{uuid}")
    public void markBankBranchAsDeletedById(@PathVariable("uuid") UUID uuid) {
        bankBranchService.markBankBranchAsDeletedById(uuid);
    }

    @DeleteMapping(value = "/delete/by/id/{uuid}")
    public void deleteBankBranchById(@PathVariable("uuid") UUID uuid) {
        bankBranchService.deleteBankBranchById(uuid);
    }

    @DeleteMapping(value = "/delete/all")
    public void deleteAllBankBranch() {
        bankBranchService.deleteAllBankBranch();
    }
}