package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGenerator;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class FakerDataGeneratorImpl implements RandomDataGenerator {
    private final TransactionRepository transactionRepository;
    @Value(value = "${randomDataGenerator.startYear}")
    private int startYear;

    @Value(value = "${randomDataGenerator.maxTransactionValue}")
    private long maxTransactionValue;

    /**
     * Generate random transactions
     *
     * @param quantity set how many transactions will be generated
     */
    @Override
    @Transactional
    public void generateTransactions(int quantity) {
        Faker faker = new Faker();
        Date date = faker.date().between(new Date(2000), new Date(2023));
        log.info(date.toString());
        Transaction transaction = new Transaction();
        //transaction.setCreated(date);
        /*
        for (int i = 0; i < quantity; i++) {
            Transaction transaction = new Transaction();
            transaction.setEmitterIban(generateIban());
            transaction.setReceiverIban(generateIban());
            transaction.setAmount(generateAmount());
            transaction.setCreated(new LocalDateTime(faker.date())); // Search
            // library!
            log.info(transaction.toString());
            transactionRepository.save(transaction);
        }
         */
    }
}