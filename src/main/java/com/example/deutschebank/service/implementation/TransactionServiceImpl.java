package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.TransactionDTOConverter;
import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.model.transaction.CreateTransactionDTO;
import com.example.deutschebank.model.transaction.GetTransactionDTO;
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
        log.info("Entity successfully created.");
    }

    @Override
    public List<GetTransactionDTO> getAllTransactions() {
        List<Transaction> getAllDTOs = transactionRepository.findAll();
        return transactionDTOConverter.convertTransactionsToGetDTOs(getAllDTOs);
    }

    @Override
    public List<GetTransactionDTO> getTransactionsByEmitterIBAN(String iban) {
        List<Transaction> allTransactions =
                transactionRepository.getTransactionsByEmitterIban(iban);
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransactions);
    }

    @Override
    public List<GetTransactionDTO> getTransactionsByReceiverIBAN(String iban) {
        List<Transaction> allTransactions =
                transactionRepository.getTransactionsByReceiverIban(iban);
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransactions);
    }

    @Override
    public List<GetTransactionDTO> getTransactionsByAmountBetween(BigDecimal from,
                                                            BigDecimal to) {
        List<Transaction> allTransaction =
                transactionRepository.getTransactionsByAmountBetween(from, to);
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransaction);
    }

    @Override
    public List<GetTransactionDTO> getTransactionsByCreatedBetween(LocalDateTime from,
                                                             LocalDateTime to) {
        List<Transaction> allTransaction =
                transactionRepository.getTransactionsByCreatedBetween(from, to);
        return transactionDTOConverter.convertTransactionsToGetDTOs(allTransaction);
    }
}