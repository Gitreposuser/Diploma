package com.example.deutschebank.service.implementation.additionaltools;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

public class BankFaker extends Faker {
    public String generateIban() {
        String iban = generateCountry();
        iban += generateCheckNumbers();
        iban += generateIFC();
        iban += generateAccountNumber();
        return iban;
    }

    public String generateCountry() {
        return this.country().countryCode2().toUpperCase();
    }

    private String generateCheckNumbers() {
        final int checkNumber = 2;
        return this.number().digits(checkNumber);
    }

    private String generateIFC() {
        final int ifcLength = 5;
        return this.number().digits(ifcLength);
    }

    private String generateAccountNumber() {
        final int accountNumber = 14;
        return this.number().digits(accountNumber);
    }

    public BigDecimal generateAmount() {
        final int maxNumberOfDecimals = 2;
        final int maxTransactionValue = 100000;
        final int minAmount = 1;
        double value = this.number().randomDouble(maxNumberOfDecimals,
                minAmount, maxTransactionValue);
        return new BigDecimal(value);
    }
}
