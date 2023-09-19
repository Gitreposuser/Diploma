package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.entity.*;
import com.example.deutschebank.entity.enums.BranchStatus;
import com.example.deutschebank.service.interfaces.additionaltools
        .RandomDataGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@Primary
public class FakerDataGeneratorImpl implements RandomDataGenerator {
    private final BankDataFaker faker;
    @Value(value = "${randomDataGenerator.startYear}")
    private int startYear;

    @Value(value = "${randomDataGenerator.maxTransactionValue}")
    private long maxTransactionValue;

    @Override
    public BankBranch generateBankBranch(int branchNumber, UUID locationId) {
        BankBranch bankBranch = new BankBranch();
        bankBranch.setBranchNumber(branchNumber);
        bankBranch.setBranchStatus(faker.generateBranchStatus());
        bankBranch.setLocationId(locationId);
        bankBranch.setGeneralPhone(faker.phoneNumber().phoneNumber());
        bankBranch.setHotLine(faker.phoneNumber().cellPhone());
        Boolean active;
        if (bankBranch.getBranchStatus() == BranchStatus.CLOSED) {
            active = false;
        } else {
            active = true;
        }
        bankBranch.setActive(active);
        log.info(bankBranch.toString());
        return bankBranch;
    }

    @Override
    public BankInfo generateBankInfo() {
        final int bankInfoId = 1;
        final long bankInfoBalance = 0;
        final long bankInfoCapitalization = 700000000;

        BankInfo bankInfo = new BankInfo();
        bankInfo.setId(bankInfoId);
        bankInfo.setName("DeutscheBank");
        bankInfo.setIban(faker.generateIban());
        bankInfo.setBalance(new BigDecimal(bankInfoBalance));
        bankInfo.setCapitalization(new BigDecimal(bankInfoCapitalization));
        bankInfo.setOwner(faker.name().fullName());
        bankInfo.setGroup(faker.company().industry());
        bankInfo.setLogoUrl("src/main/resources/static" +
                "/images/banklogo.png");
        log.info(bankInfo.toString());
        return bankInfo;
    }

    @Override
    public Client generateClient(UUID managerId,
                                 UUID debitAccountId,
                                 UUID personalDetailId,
                                 UUID locationId) {
        Client client = new Client();
        client.setManagerId(managerId);
        client.setDebitAccountId(debitAccountId);
        client.setPersonalDetailId(personalDetailId);
        client.setLocationId(locationId);
        client.setActive(faker.bool().bool());
        log.info(client.toString());
        return client;
    }

    @Override
    public CreditAccount generateCreditAccount(UUID clientId) {
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setClientId(clientId);
        creditAccount.setCreditStatus(faker.generateCreditStatus());
        final int minCreditValue = 2000;
        final int maxCreditValue = 500000;
        creditAccount.setDebt(new BigDecimal(faker.number()
                .numberBetween(minCreditValue, maxCreditValue)));
        creditAccount.setStartFrom(faker
                .generateDateTimeFromYearToNow(startYear));
        creditAccount.setActive(faker.bool().bool());
        log.info(creditAccount.toString());
        return creditAccount;
    }

    @Override
    public DebitAccount generateDebitAccount() {
        DebitAccount debitAccount = new DebitAccount();
        debitAccount.setDebitStatus(faker.generateDebitStatus());
        debitAccount.setBalance(faker.generateAmount());
        debitAccount.setDepositInterest(new BigDecimal(2.5));
        debitAccount.setCreditLine(new BigDecimal(5000));
        debitAccount.setStartFrom(faker
                .generateDateTimeFromYearToNow(startYear));
        debitAccount.setActive(faker.bool().bool());
        log.info(debitAccount.toString());
        return debitAccount;
    }

    @Override
    public Employee generateEmployee(UUID personalDetailId,
                                     UUID workDetailId,
                                     UUID locationId,
                                     UUID branchId) {
        Employee employee = new Employee();
        employee.setPersonalDetailId(personalDetailId);
        employee.setWorkDetailId(workDetailId);
        employee.setLocationId(locationId);
        employee.setBranchId(branchId);
        employee.setActive(faker.bool().bool());
        log.info(employee.toString());
        return employee;
    }

    @Override
    public Location generateLocation() {
        Location location = new Location();
        location.setHouseNumber(Integer.parseInt(faker.address()
                .buildingNumber()));
        location.setStreet(faker.address().streetAddress());
        location.setCity(faker.address().city());
        location.setState(faker.address().state());
        location.setCountry(faker.address().country());
        location.setActive(true);
        log.info(location.toString());
        return location;
    }

    @Override
    public PersonalDetail generatePersonalDetail() {
        PersonalDetail personalDetail = new PersonalDetail();
        personalDetail.setFirstName(faker.name().firstName());
        personalDetail.setLastName(faker.name().lastName());
        personalDetail.setSex(faker.generateSex());
        personalDetail.setPhone(faker.phoneNumber().cellPhone());
        String funnyName = faker.funnyName().name().replace(' ',
                '_');
        personalDetail.setEmail(faker.internet().emailAddress(funnyName));
        final int startYear = 1960;
        final int endYear = 2005;
        personalDetail.setBirthDate(faker.generateDateTimeYearFromTo(startYear,
                endYear));
        final int age =
                LocalDateTime.now().getYear() - personalDetail.getBirthDate()
                        .getYear();
        personalDetail.setAge(age);
        final boolean isMarried = faker.bool().bool();
        personalDetail.setIsMarried(isMarried);
        personalDetail.setChildren(faker.generateChildren(isMarried));
        personalDetail.setActive(faker.bool().bool());
        log.info(personalDetail.toString());
        return personalDetail;
    }

    @Override
    public Transaction generateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setEmitterIban(faker.finance().iban());
        transaction.setReceiverIban(faker.finance().iban());
        transaction.setAmount(generateAmount());
        log.info(transaction.toString());
        return transaction;
    }

    @Override
    public WorkDetail generateWorkDetail() {
        WorkDetail workDetail = new WorkDetail();
        workDetail.setPosition(faker.job().position());
        workDetail.setWorkStatus(faker.generateAcurateWorkStatus());
        final int minSalary = 2500;
        final int maxSalary = 75000;
        workDetail.setSalary(new BigDecimal(faker.number()
                .numberBetween(minSalary, maxSalary)));
        workDetail.setWorkPhone(faker.phoneNumber().cellPhone());
        workDetail.setWorkEmail(faker.internet().emailAddress());
        final int startYear = 1991;
        workDetail.setStartFrom(faker.generateDateTimeFromYearToNow(startYear));
        workDetail.setEndAt(null);
        workDetail.setActive(true);
        log.info(workDetail.toString());
        return workDetail;
    }

    private String generateIban() {
        String iban = generateCountry();
        iban += generateCheckNumbers();
        iban += generateIFC();
        iban += generateAccountNumber();
        return iban;
    }

    private String generateCountry() {
        return faker.country().countryCode2().toUpperCase();
    }

    private String generateCheckNumbers() {
        final int checkNumber = 2;
        return faker.number().digits(checkNumber);
    }

    private String generateIFC() {
        final int ifcLength = 5;
        return faker.number().digits(ifcLength);
    }

    private String generateAccountNumber() {
        final int accountNumber = 14;
        return faker.number().digits(accountNumber);
    }

    private BigDecimal generateAmount() {
        final int maxNumberOfDecimals = 2;
        final int minAmount = 1;
        double value = faker.number().randomDouble(maxNumberOfDecimals,
                minAmount, maxTransactionValue);
        return new BigDecimal(value);
    }
}