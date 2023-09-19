package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.entity.enums.*;
import com.example.deutschebank.exception.BadRangeException;
import com.example.deutschebank.exception.NegativeValueException;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.*;
import java.util.Random;

public class BankDataFaker extends Faker {
    private final Random rnd;

    public BankDataFaker() {
        rnd = new Random();
    }

    public int generateChildren(boolean isMarried) {
        if(isMarried) {
            final int minChildren = 0;
            final int maxChildren = 5;
            return number().numberBetween(minChildren, maxChildren);
        }
        final int noChildren = 0;
        return noChildren;
    }

    public Sex generateSex() {
        if (bool().bool()) {
            return Sex.M;
        } else {
            return Sex.W;
        }
    }

    public BranchStatus generateBranchStatus() {
        final int randomIndex = rnd.nextInt(BranchStatus.values().length);
        return BranchStatus.values()[randomIndex];
    }

    public CreditStatus generateCreditStatus() {
        final int randomIndex = rnd.nextInt(CreditStatus.values().length);
        return CreditStatus.values()[randomIndex];
    }

    public DebitStatus generateDebitStatus() {
        final int randomIndex = rnd.nextInt(DebitStatus.values().length);
        return DebitStatus.values()[randomIndex];
    }

    public WorkStatus generateAcurateWorkStatus() {
        final int maxChance = 100;
        final int choice = rnd.nextInt(maxChance);
        final int startWorkingChance = 0;
        final int endWorkingChance = 60;
        if (choice > startWorkingChance && choice <= endWorkingChance) {
            return WorkStatus.WORKING;
        }
        final int startVacationChance = 60;
        final int endVacationChance = 80;
        if (choice > startVacationChance && choice <= endVacationChance) {
            return WorkStatus.ON_VACATION;
        }
        final int startTreatmentChance = 80;
        final int endTreatmentChance = 90;
        if (choice > startTreatmentChance && choice <= endTreatmentChance) {
            return WorkStatus.ON_TREATMENT;
        }
        final int startMaternityChance = 90;
        final int endMaternityChance = 95;
        if (choice > startMaternityChance && choice <= endMaternityChance) {
            return WorkStatus.ON_MATERNITY_LEAVE;
        }
        return WorkStatus.FIRED;
    }

    public WorkStatus generateWorkStatus() {
        final int randomIndex = rnd.nextInt(WorkStatus.values().length);
        return WorkStatus.values()[randomIndex];
    }

    public LocalTime generateRandomTime() {
        final int startFrom = 0;
        final int randomHour = rnd.nextInt(startFrom,
                LocalDateTime.MAX.getHour());
        final int randomMinute = rnd.nextInt(startFrom,
                LocalDateTime.MAX.getMinute());
        final int randomSecond = rnd.nextInt(startFrom,
                LocalDateTime.MAX.getSecond());
        return LocalTime.of(randomHour, randomMinute, randomSecond);
    }

    public LocalTime generateTimeBetween(int fromHour, int toHour) {
        rangeValidator(fromHour, toHour);
        rangeValidator(fromHour, toHour);
        final int startFrom = 0;
        final int hourBetween = rnd.nextInt(fromHour, toHour);
        final int randomMinute = rnd.nextInt(startFrom,
                LocalDateTime.MAX.getMinute());
        final int randomSecond = rnd.nextInt(startFrom,
                LocalDateTime.MAX.getSecond());
        return LocalTime.of(hourBetween, randomMinute, randomSecond);
    }

    public LocalDateTime generateDateTimeYearFromTo(int fromYear, int toYear) {
        rangeValidator(fromYear, toYear);
        negativeValueValidator(fromYear, toYear);
        final int startingFrom = 1;
        final int randomYear = rnd.nextInt(fromYear, toYear);
        final int randomMonth = rnd.nextInt(startingFrom,
                LocalDateTime.MAX.getMonthValue());
        LocalDate month = LocalDate.of(randomYear, randomMonth, startingFrom);
        final int randomDate = rnd.nextInt(startingFrom,
                month.lengthOfMonth());

        LocalTime time = generateRandomTime();
        LocalDateTime randomDateTime = LocalDateTime.of(randomYear,
                randomMonth, randomDate, time.getHour(), time.getMinute(),
                time.getSecond());
        return randomDateTime;
    }

    public LocalDateTime generateDateTimeFromYearToNow(int startYear) {
        negativeValueValidator(startYear);
        return generateDateTimeYearFromTo(startYear, LocalDate.now().getYear());
    }

    public String generateIban() {
        String iban = generateCountry();
        iban += generateCheckNumbers();
        iban += generateIFC();
        iban += generateAccountNumber();
        return iban;
    }

    public String generateCountry() {
        return country().countryCode2().toUpperCase();
    }

    private String generateCheckNumbers() {
        final int checkNumber = 2;
        return number().digits(checkNumber);
    }

    private String generateIFC() {
        final int ifcLength = 5;
        return number().digits(ifcLength);
    }

    private String generateAccountNumber() {
        final int accountNumber = 14;
        return number().digits(accountNumber);
    }

    public BigDecimal generateAmount() {
        final int maxNumberOfDecimals = 2;
        final int maxTransactionValue = 100000;
        final int minAmount = 1;
        double value = number().randomDouble(maxNumberOfDecimals,
                minAmount, maxTransactionValue);
        return new BigDecimal(value);
    }

    private void rangeValidator(int from, int to) {
        if (from >= to) {
            throw new BadRangeException("Wrong range values!");
        }
    }

    private void negativeValueValidator(int value) {
        if (value < 0) {
            throw new NegativeValueException("Negative value!");
        }
    }

    private void negativeValueValidator(int from, int to) {
        if ((from < 0) || (to < 0)) {
            throw new NegativeValueException("Negative value!");
        }
    }
}