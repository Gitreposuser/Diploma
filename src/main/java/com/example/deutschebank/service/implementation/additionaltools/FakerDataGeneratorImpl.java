package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.config.FakerConfig;
import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGenerator;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
@Primary
public class FakerDataGeneratorImpl implements RandomDataGenerator {
    private final TransactionRepository transactionRepository;
    private final FakerConfig faker;
    @Value(value = "${randomDataGenerator.startYear}")
    private int startYear;

    @Value(value = "${randomDataGenerator.maxTransactionValue}")
    private long maxTransactionValue;

    @Override
    @Transactional
    public void generateTransactionToDB() {
        Transaction transaction = new Transaction();
        transaction.setEmitterIban(generateIban());
        transaction.setReceiverIban(generateIban());
        transaction.setAmount(generateAmount());
        transaction.setCreated(generateDateTime());
        log.info(transaction.toString());
        transactionRepository.save(transaction);
    }

    /**
     * Generate random transactions
     *
     * @param quantity set how many transactions will be generated
     */
    @Override
    public void generateTransactionsToDB(int quantity) {
        for (int i = 0; i < quantity; i++) {
            generateTransactionToDB();
        }
    }

    private LocalDateTime generateDateTime() {
        return null;
    }

    private String generateIban() {
        String iban = generateCountry();
        iban += generateCheckNumbers();
        iban += generateIFC();
        iban += generateAccountNumber();
        return iban;
    }

    private String generateCountry() {
        return faker.modelFaker().country().countryCode2().toUpperCase();
    }

    private String generateCheckNumbers() {
        final int checkNumber = 2;
        return faker.modelFaker().number().digits(checkNumber);
    }

    private String generateIFC() {
        final int ifcLength = 5;
        return faker.modelFaker().number().digits(ifcLength);
    }

    private String generateAccountNumber() {
        final int accountNumber = 14;
        return faker.modelFaker().number().digits(accountNumber);
    }

    private BigDecimal generateAmount() {
        final int maxNumberOfDecimals = 2;
        final int minAmount = 1;
        double value = faker.modelFaker().number().randomDouble(maxNumberOfDecimals,
                minAmount, maxTransactionValue);
        return new BigDecimal(value);
    }
}