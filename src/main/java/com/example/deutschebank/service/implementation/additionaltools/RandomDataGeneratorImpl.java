package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGenerator;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Generate random data for transactions
 */
@Slf4j
@Component
@RequiredArgsConstructor
//@Primary
public class RandomDataGeneratorImpl implements RandomDataGenerator {
    private final TransactionRepository transactionRepository;

    private Random rnd;
    @Value(value = "${randomDataGenerator.startYear}")
    private int startYear;

    @Value(value = "${randomDataGenerator.maxTransactionValue}")
    private long maxTransactionValue;

    @PostConstruct
    public void init() {
        rnd = new Random();
    }

    /**
     * Generate random transaction to current Database
     */
    @Override
    @Transactional
    public void generateTransactionToDB() {
        Transaction transaction = new Transaction();
        transaction.setEmitterIban(generateIban());
        transaction.setReceiverIban(generateIban());
        transaction.setAmount(generateAmount());
        transaction.setCreated(generateDateTime()); // Search library!
        transactionRepository.save(transaction);
        log.info(transaction.toString());
    }

    /**
     * Generate random transactions to DB
     *
     * @param quantity set how many transactions will be generated
     */
    @Override
    public void generateTransactionsToDB(int quantity) {
        for (int i = 0; i < quantity; i++) {
            generateTransactionToDB();
        }
    }

    /**
     * Generates a LocalDateTime object representing the current date and time.
     *
     * @return The LocalDateTime object representing the current date and time.
     */
    private LocalDateTime generateDateTime() {
        final int startingMonthOrDay = 1;
        final int randomYear = rnd.nextInt(startYear, LocalDateTime.now().getYear());
        final int randomMonth = rnd.nextInt(startingMonthOrDay,
                LocalDateTime.MAX.getMonthValue());
        LocalDate month = LocalDate.of(randomYear, randomMonth, startingMonthOrDay);
        final int randomDate = rnd.nextInt(startingMonthOrDay,
                month.lengthOfMonth());

        final int startingHourOrMinute = 0;
        final int randomHour = rnd.nextInt(startingHourOrMinute, LocalDateTime.MAX.getHour());
        final int randomMinute = rnd.nextInt(startingHourOrMinute, LocalDateTime.MAX.getMinute());
        final int randomSecond = rnd.nextInt(startingHourOrMinute, LocalDateTime.MAX.getSecond());

        LocalDateTime randomDateTime = LocalDateTime.of(randomYear,
                randomMonth, randomDate,
                randomHour, randomMinute, randomSecond);
        return randomDateTime;
    }

    /**
     * Generate random complete IBAN number
     * based on predefined conditions
     * in form of country where AA - 2 letters,
     * BB - check number 2 digits, CCCCCC - (International Financial Code) 5
     * digits, DDDDD - five zeros to complete clients account number,
     * NNNNNNNNNNNNNN - card number 14 digits
     *
     * @return IBAN string
     */
    private String generateIban() {
        String iban = generateCountry();
        iban += generateCheckNumbers();
        iban += generateIFC();
        iban += generateAccountNumber();
        return iban;
    }

    /**
     * Generate country identity
     * two letters XX in upper case
     * for example "US", "DE", "UK"
     *
     * @return String witch represents country identity
     */
    private String generateCountry() {
        String[] country = {"DE", "UA", "GB", "FR", "NL", "PL", "ND", "UK"};
        return country[rnd.nextInt(country.length)];
    }

    /**
     * Generate check numbers
     * two numbers XX from 0 to 9
     * for example "00", "75", "12"
     *
     * @return String of two digits
     * which represents check number
     */
    private String generateCheckNumbers() {
        final int checkNumber = 2;  // Set as class field with static!
        return generateNumbersOfLength(checkNumber);
    }

    /**
     * International Financial Code 5 random digits
     *
     * @return String which represents international
     * financial code of five digits
     */
    private String generateIFC() {
        final int ifcLength = 5;
        return generateNumbersOfLength(ifcLength);
    }

    /**
     * Generate account number
     *
     * @return String of 14 digits which represents account number
     */
    private String generateAccountNumber() {
        // Account number length
        final int accountNumber = 14;
        return generateNumbersOfLength(accountNumber);
    }

    /**
     * Generate random String representation of n - digits
     *
     * @param length defines quantity of generated digits
     * @return String representation of randomly generated numbers
     */
    private String generateNumbersOfLength(int length) {
        String number = "";
        for (int i = 0; i < length; i++) {
            number += rnd.nextInt(9);
        }
        return number;
    }

    /**
     * Generate transfer amount bigDecimal number
     * where decimal part is from 0 to 99
     *
     * @return random number which represents
     * transfer amount
     */
    private BigDecimal generateAmount() {
        return BigDecimal.valueOf(rnd.nextDouble(maxTransactionValue));
    }
}
