package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.entity.*;
import com.example.deutschebank.entity.enums.BranchStatus;
import com.example.deutschebank.entity.enums.CreditStatus;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Primary
public class FakerDataGeneratorImpl implements RandomDataGeneratorService {
    private final BankDataFaker faker;

    @Value(value = "${bankInfoService.id}")
    private Integer bankId;

    @Value(value = "${bankInfoService.name}")
    private String bankName;

    @Value(value = "${bankInfoService.iban}")
    private String bankIban;

    @Value(value = "${bankInfoService.balance}")
    private String bankBalance;

    @Value(value = "${bankInfoService.capitalization}")
    private String bankCapitalization;

    @Value(value = "${bankInfoService.owner}")
    private String bankOwner;

    @Override
    public <T> T chooseFromList(List<T> chooseList) {
        return faker.chooseFromList(chooseList);
    }

    @Override
    public BankBranch generateBankBranch(Integer branchNumber, Location location) {
        BankBranch bankBranch = new BankBranch();
        bankBranch.setBranchNumber(branchNumber);
        bankBranch.setBranchStatus(faker.generateBranchStatus());
        bankBranch.setLocation(location);
        bankBranch.setGeneralPhone(faker.phoneNumber().phoneNumber());
        bankBranch.setHotLine(faker.phoneNumber().cellPhone());
        boolean active = (bankBranch.getBranchStatus() != BranchStatus.CLOSED);
        bankBranch.setActive(active);
        log.info("Generate bank branch: " + bankBranch);
        return bankBranch;
    }

    @Override
    public List<BankBranch> generateMultipleBankBranches(Integer quantity,
                                                         List<Location> locations) {
        List<BankBranch> bankBranches = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            bankBranches.add(generateBankBranch(i + 1,
                    locations.get(i)));
        }
        return bankBranches;
    }

    @Override
    public BankAccount generateBankInfo() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankId);
        bankAccount.setName(bankName);
        bankAccount.setIban(bankIban);
        bankAccount.setBalance(new BigDecimal(bankBalance));
        bankAccount.setCapitalization(new BigDecimal(bankCapitalization));
        bankAccount.setOwner(bankOwner);
        bankAccount.setGroup(faker.company().industry());
        bankAccount.setLogoUrl("src/main/resources/static" +
                "/images/banklogo.png");
        log.info("Generate bank info: " + bankAccount);
        return bankAccount;
    }

    @Override
    public Client generateClient(PersonalDetail personalDetail,
                                 DebitAccount debitAccount,
                                 Location location,
                                 Employee employee) {
        Client client = new Client();
        client.setPersonalDetail(personalDetail);
        client.setDebitAccount(debitAccount);
        client.setLocation(location);
        client.setEmployee(employee);
        client.setActive(faker.bool().bool());
        log.info("Generate client: " + client);
        return client;
    }

    @Override
    public List<Client> generateMultipleClients(Integer quantity,
                                                List<PersonalDetail> personalDetails,
                                                List<DebitAccount> debitAccounts,
                                                List<Location> locations,
                                                List<Employee> employees) {
        List<Client> clients = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            clients.add(generateClient(personalDetails.get(i),
                    debitAccounts.get(i),
                    locations.get(i),
                    chooseFromList(employees)));
        }
        log.info("Generate multiple clients, quantity: " + quantity);
        return clients;
    }

    @Override
    public CreditAccount generateCreditAccount(Client client) {
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setClient(client);
        creditAccount.setReason(faker.commerce().productName());
        creditAccount.setCreditStatus(faker.generateCreditStatus());
        BigDecimal creditAmount;
        boolean isCreditActive;
        if(creditAccount.getCreditStatus() == CreditStatus.CLOSED) {
            creditAmount = new BigDecimal(0);
            isCreditActive = false;
        } else {
            final int minCreditValue = 100;
            final int maxCreditValue = 500000;
            creditAmount =
                    new BigDecimal(faker.number()
                            .numberBetween(minCreditValue, maxCreditValue));
            isCreditActive = true;
        }
        creditAccount.setDebt(creditAmount);
        final BigDecimal loanInterest = new BigDecimal(3);
        creditAccount.setLoanInterest(loanInterest);
        creditAccount.setActive(isCreditActive);
        log.info("Generate credit account: " + creditAccount);
        return creditAccount;
    }

    @Override
    public List<CreditAccount> generateMultipleCreditAccounts(Integer quantity,
                                                              List<Client> clients) {
        List<CreditAccount> creditAccounts = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            creditAccounts.add(generateCreditAccount(chooseFromList(clients)));
        }
        log.info("Generate multiple credit accounts, quantity: " + quantity);
        return creditAccounts;
    }

    @Override
    public DebitAccount generateDebitAccount() {
        DebitAccount debitAccount = new DebitAccount();
        debitAccount.setIban(faker.finance().iban());
        debitAccount.setDebitStatus(faker.generateDebitStatus());
        debitAccount.setBalance(faker.generateAmount());
        final BigDecimal debitInterest = new BigDecimal("2.5");
        debitAccount.setDepositInterest(debitInterest);
        final BigDecimal creditAmount = new BigDecimal(5000);
        debitAccount.setCreditLine(creditAmount);
        log.info("Generate debit account: " + debitAccount);
        return debitAccount;
    }

    @Override
    public List<DebitAccount> generateMultipleDebitAccounts(Integer quantity) {
        List<DebitAccount> debitAccounts = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            debitAccounts.add(generateDebitAccount());
        }
        log.info("Generate multiple debit accounts, quantity: " + quantity);
        return debitAccounts;
    }

    @Override
    public Employee generateEmployee(PersonalDetail personalDetail,
                                     WorkDetail workDetail,
                                     Location location,
                                     BankBranch bankBranch) {
        Employee employee = new Employee();
        employee.setPersonalDetail(personalDetail);
        employee.setWorkDetail(workDetail);
        employee.setLocation(location);
        employee.setBankBranch(bankBranch);
        employee.setActive(faker.bool().bool());
        log.info("Generate employee: " + employee);
        return employee;
    }

    @Override
    public List<Employee> generateMultipleEmployees(Integer quantity,
                                                    List<PersonalDetail> personalDetails,
                                                    List<WorkDetail> workDetails,
                                                    List<Location> locations,
                                                    List<BankBranch> bankBranches) {
        List<Employee> employees = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            employees.add(generateEmployee(personalDetails.get(i),
                    workDetails.get(i),
                    locations.get(i),
                    chooseFromList(bankBranches)));
        }
        log.info("Generate multiple employees, quantity: " + quantity);
        return employees;
    }

    @Override
    public Location generateLocation() {
        Location location = new Location();
        location.setHouseNumber(Integer.parseInt(faker.address()
                .buildingNumber()));
        location.setStreet(faker.address().streetName());
        location.setCity(faker.address().city());
        location.setState(faker.address().state());
        location.setCountry(faker.address().country());
        log.info("Generate location: " + location);
        return location;
    }

    @Override
    public List<Location> generateMultipleLocations(Integer quantity) {
        List<Location> locations = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            locations.add(generateLocation());
        }
        log.info("Generate multiple locations, quantity: " + quantity);
        return locations;
    }

    @Override
    public PersonalDetail generatePersonalDetail() {
        PersonalDetail personalDetail =
                new PersonalDetail();
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
        log.info("Generate personal detail: " + personalDetail);
        return personalDetail;
    }

    @Override
    public List<PersonalDetail> generateMultiplePersonalDetails(Integer quantity) {
        List<PersonalDetail> personalDetails = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            personalDetails.add(generatePersonalDetail());
        }
        log.info("Generate multiple personal details, quantity: " + quantity);
        return personalDetails;
    }

    @Override
    public Transaction generateTransaction(Client emitter, Client receiver) {
        Transaction transaction = new Transaction();
        transaction.setEmitterIban(emitter.getDebitAccount().getIban());
        transaction.setReceiverIban(receiver.getDebitAccount().getIban());
        transaction.setAmount(new BigDecimal(faker.generateAmount().toString()));
        log.info("Generate transaction, amount: " + transaction.getAmount());
        return transaction;
    }

    @Override
    public List<Transaction> generateMultipleTransactions(Integer quantity,
                                                          List<Client> emitters,
                                                          List<Client> receivers) {
        List<Transaction> transactions = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            Client emitter = chooseFromList(emitters);
            Client receiver;
            do {
                receiver = chooseFromList(receivers);
            } while(emitter == receiver);
            transactions.add(generateTransaction(emitter, receiver));
        }
        log.info("Generate multiple transactions, quantity: " + transactions.size());
        return transactions;
    }

    @Override
    public WorkDetail generateWorkDetail() {
        WorkDetail workDetail = new WorkDetail();
        workDetail.setPosition(faker.job().position());
        workDetail.setWorkStatus(faker.generateWorkStatus());
        final int minSalary = 2500;
        final int maxSalary = 75000;
        workDetail.setSalary(new BigDecimal(faker.number()
                .numberBetween(minSalary, maxSalary)));
        workDetail.setWorkPhone(faker.phoneNumber().cellPhone());
        workDetail.setWorkEmail(faker.internet().emailAddress());
        final int startYear = 1991;
        workDetail.setStartFrom(faker.generateDateTimeFromYearToNow(startYear));
        workDetail.setEndAt(null);
        log.info("Generate work detail: " + workDetail);
        return workDetail;
    }

    @Override
    public List<WorkDetail> generateMultipleWorkDetails(Integer quantity) {
        List<WorkDetail> workDetails = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            workDetails.add(generateWorkDetail());
        }
        log.info("Generate multiple work details, quantity: " + quantity);
        return workDetails;
    }
}