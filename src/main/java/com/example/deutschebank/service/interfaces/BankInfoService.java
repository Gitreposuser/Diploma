package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.BankInfo;

import java.util.UUID;

public interface BankInfoService {
    void create(BankInfo bankInfo);
    BankInfo read(UUID id);
    boolean update(UUID id, BankInfo bankInfo);
    boolean delete(UUID id);
}
