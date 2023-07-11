package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.TransactionService;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    public final TransactionRepository transactionRepository;

    /**
     * Create transaction with emitter, receiver IBAN and amount
     * of transfer
     *
     * @param transaction
     */
    @Override
    public void createTransaction(Transaction transaction) {
        transaction.setCreated(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    /**
     * Get all existing transactions
     *
     * @return list of all transactions
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Get all transactions by emitter IBAN
     *
     * @param iban emitter IBAN
     * @return list of transactions which corresponds emitter IBAN
     */
    @Override
    public List<Transaction> getTransactionsByEmitterIBAN(String iban) {
        return transactionRepository.getTransactionsByEmitterIban(iban);
    }

    /**
     * Get all transactions by receiver IBAN
     *
     * @param iban receiver IBAN
     * @return list of transactions which corresponds receiver IBAN
     */
    @Override
    public List<Transaction> getTransactionsByReceiverIBAN(String iban) {
        return transactionRepository.getTransactionsByReceiverIban(iban);
    }

    /**
     * Get all transaction by amount in range from - to.
     *
     * @param from starting range amount
     * @param to   ending amount
     * @return
     */
    @Override
    public List<Transaction> getTransactionsByAmountBetween(BigDecimal from,
                                                            BigDecimal to) {
        return transactionRepository.getTransactionsByAmountBetween(from, to);
    }

    /**
     * Get all transactions in range from - to. By time creation where
     *
     * @param from starting date from when
     * @param to   ending date to
     * @return list of all transactions in date time range
     */
    @Override
    public List<Transaction> getTransactionsByCreatedBetween(LocalDateTime from,
                                                             LocalDateTime to) {
        return transactionRepository.getTransactionsByCreatedBetween(from, to);
    }
}