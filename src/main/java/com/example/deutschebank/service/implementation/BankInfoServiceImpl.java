package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.repository.BankInfoRepository;
import com.example.deutschebank.service.interfaces.BankInfoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankInfoServiceImpl implements BankInfoService {
    private final BankInfoRepository bankInfoRepository;

    @Override
    public void create(BankInfo bankInfo) {
        bankInfoRepository.save(bankInfo);
    }

    @Override
    public BankInfo read(UUID id) {
        Optional<BankInfo> bankInfo =  bankInfoRepository
                .findById(id);
        if(bankInfo.isPresent()) {
            return bankInfo.get();
        } else {
            throw new RuntimeException("Exception occurred!");
        }
    }

    @Override
    public boolean update(UUID id, BankInfo bankInfo) {
        BankInfo info = bankInfoRepository.getReferenceById(id);
        if (bankInfo.getName() != null &&
                !info.getName().equalsIgnoreCase(bankInfo.getName())){
            info.setName(bankInfo.getName());
        }

        if (bankInfo.getIban() != null &&
                !info.getIban().equalsIgnoreCase(bankInfo.getIban())){
            info.setIban(bankInfo.getIban());
        }

        if (bankInfo.getBalance() != null){
            info.setBalance(bankInfo.getBalance());
        }

        if (bankInfo.getCapitalization() != null){
            info.setCapitalization(bankInfo.getCapitalization());
        }

        if (bankInfo.getOwner() != null){
            info.setOwner(bankInfo.getOwner());
        }

        if (bankInfo.getGroup() != null){
            info.setGroup(bankInfo.getGroup());
        }

        if (bankInfo.getBlobData() != null){
            info.setBlobData(bankInfo.getBlobData());
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }
}
