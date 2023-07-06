package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    public final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Optional<List<Transaction>> getAllTransactions() {
        return Optional.of(transactionRepository.findAll());
        //return Optional.empty();
    }

    @Override
    public Optional<Transaction> getTransactionById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByEmitterIBAN(String iban) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByReceiverIBAN(String iban) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByAmountInRange(int from, int to) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByDate(LocalDate date) {
        return Optional.empty();
    }

    public void generateTransactions(int quantity) {
        for (int i = 0; i < quantity; i++) {
            Transaction transaction = new Transaction();
            transaction.setEmitterIban(generateIban());
            transaction.setReceiverIban(generateIban());
            transaction.setAmount(generateAmount());
            transaction.setCreated(LocalDateTime.now());
            log.info(transaction.toString());
            transactionRepository.save(transaction);
        }
    }

    private String generateIban() {
        Random rnd = new Random();
        String iban = "";
        for (int i = 0; i < 12; i++) {
            iban += rnd.nextInt(9);
        }
        return iban;
    }

    private BigDecimal generateAmount() {
        Random rnd = new Random();
        return BigDecimal.valueOf(rnd.nextInt(100000));
    }
}
