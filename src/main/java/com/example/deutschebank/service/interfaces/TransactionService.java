package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.model.transaction.CreateTransactionDTO;
import com.example.deutschebank.model.transaction.GetTransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    void createTransaction(CreateTransactionDTO createDTO);

    List<GetTransactionDTO> getAllTransactions();

    List<GetTransactionDTO> getTransactionsByEmitterIBAN(String iban);

    List<GetTransactionDTO> getTransactionsByReceiverIBAN(String iban);

    List<GetTransactionDTO> getTransactionsByAmountBetween(BigDecimal from,
                                                           BigDecimal to);

    List<GetTransactionDTO> getTransactionsByCreatedBetween(LocalDateTime from,
                                                            LocalDateTime to);
}