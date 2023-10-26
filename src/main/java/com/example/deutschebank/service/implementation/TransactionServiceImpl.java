package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.TransactionDTOConverter;
import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.dto.transaction.CreateTransactionDTO;
import com.example.deutschebank.dto.transaction.GetTransactionDTO;
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
    private final TransactionDTOConverter transactionDTOConverter;

    @Override
    @Transactional
    public void createTransaction(CreateTransactionDTO createDTO) {
        Transaction transaction =
                transactionDTOConverter.convertCreateDTOToTransaction(createDTO);
        transactionRepository.save(transaction);
        log.info("Create transaction.");
    }

    @Override
    @Transactional
    public List<GetTransactionDTO> getAllTransactions() {
        List<Transaction> getAllDTOs = transactionRepository.findAll();
        log.info("Get all transactions, quantity: " + getAllDTOs.size());
        return transactionDTOConverter.convertTransactionsToGetDTOs(getAllDTOs);
    }

    @Override
    @Transactional
    public List<GetTransactionDTO> getTransactionsByEmitterIBAN(String iban) {
        List<Transaction> allTransactions =
                transactionRepository.getTransactionsByEmitterIban(iban);
        log.info("Get all transactions by emitter iban, quantity: " + allTransactions.size());
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransactions);
    }

    @Override
    @Transactional
    public List<GetTransactionDTO> getTransactionsByReceiverIBAN(String iban) {
        List<Transaction> allTransactions =
                transactionRepository.getTransactionsByReceiverIban(iban);
        log.info("Get all transactions by receiver iban, quantity: " + allTransactions.size());
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransactions);
    }

    @Override
    @Transactional
    public List<GetTransactionDTO> getTransactionsByAmountBetween(BigDecimal from,
                                                            BigDecimal to) {
        List<Transaction> allTransaction =
                transactionRepository.getTransactionsByAmountBetween(from, to);
        log.info("Get all transactions by amount between, quantity: " + allTransaction.size());
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransaction);
    }

    @Override
    @Transactional
    public List<GetTransactionDTO> getTransactionsByCreatedBetween(LocalDateTime from,
                                                             LocalDateTime to) {
        List<Transaction> allTransaction =
                transactionRepository.getTransactionsByCreatedBetween(from, to);
        log.info("Get all transactions by date between, quantity: " + allTransaction.size());
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransaction);
    }

    @Override
    @Transactional
    public BigDecimal getMinAmountByEmitterIban(String iban) {
        BigDecimal minAmount = transactionRepository
                .getMinAmountByEmitterIban(iban);
        log.warn(minAmount.toString());
        return minAmount;
    }
}