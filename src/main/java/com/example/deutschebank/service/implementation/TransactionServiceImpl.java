package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    public final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Transaction transaction) {
        transaction.setCreated(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByEmitterIBAN(String iban) {
        return transactionRepository.getTransactionsByEmitterIban(iban);
    }

    @Override
    public List<Transaction> getTransactionsByReceiverIBAN(String iban) {
        return transactionRepository.getTransactionsByReceiverIban(iban);
    }

    @Override
    public List<Transaction> getTransactionsByAmountBetween(BigDecimal from,
                                                            BigDecimal to) {
        return transactionRepository.getTransactionsByAmountBetween(from, to);
    }

    @Override
    public List<Transaction> getTransactionsByCreatedBetween(LocalDateTime from,
                                                             LocalDateTime to) {
        return transactionRepository.getTransactionsByCreatedBetween(from, to);
    }
}