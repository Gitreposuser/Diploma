package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByEmitterIBAN(String iban) {
        return Optional.of(transactionRepository.getTransactionsByEmitterIBAN(iban));
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByReceiverIBAN(String iban) {
        return Optional.of(transactionRepository.getTransactionsByReceiverIBAN(iban));
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByAmountInRange(int from, int to) {
        return Optional.of(transactionRepository.getTransactionsByAmountInRange(from, to));
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByDateInRange(LocalDateTime from, LocalDateTime to) {
        return Optional.of(transactionRepository.getTransactionsByDateInRange(from, to));
    }

    /**
     * Generate random transactions
     *
     * @param quantity set how many transactions will be generated
     */
    @Override
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
     * Generate random String representation of n - digits
     *
     * @param length defines quantity of generated digits
     * @return number String representation of randomly generated numbers
     */
    private String generateNumbersOfLength(int length) {
        Random rnd = new Random();
        String number = "";
        for (int i = 0; i < length; i++) {
            number += rnd.nextInt(9);
        }
        return number;
    }

    /**
     * Generate country identity
     * two letters XX in upper case
     * for example "US", "DE", "UK"
     *
     * @return String witch represents country identity
     */
    private String generateCountry() {
        String[] country = {"DE", "UA", "GB", "FR", "NL", "PL"};
        Random rnd = new Random();
        String countryIdentity = country[rnd.nextInt(country.length)];
        return countryIdentity;
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
        final int checkNumber = 2;
        return generateNumbersOfLength(checkNumber);
    }

    /**
     * International Financial Code 5 random digits
     *
     * @return String which represents international
     * financial code of five digits
     */
    private String generateIFC() {
        final int ifcLength = 5; // International Financial Code
        return generateNumbersOfLength(ifcLength);
    }

    /**
     * Generate account number
     *
     * @return String of 14 digits which represents account number
     */
    private String generateAccountNumber() {
        final int accountNumber = 14;
        return generateNumbersOfLength(accountNumber);
    }

    /**
     * Generate transfer amount bigDecimal number
     * where decimal part is from 0 to 99
     *
     * @return random BigDecimal number which represents
     * transfer amount
     */
    private BigDecimal generateAmount() {
        Random rnd = new Random();
        return BigDecimal.valueOf(rnd.nextInt(100000));
    }
}