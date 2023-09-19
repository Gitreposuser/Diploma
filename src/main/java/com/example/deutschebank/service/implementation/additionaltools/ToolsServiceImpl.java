package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.entity.*;
import com.example.deutschebank.repository.*;
import com.example.deutschebank.service.interfaces.additionaltools.ToolsService;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ToolsServiceImpl implements ToolsService {
    private final BankBranchRepository bankBranchRepository;
    private final BankInfoRepository bankInfoRepository;
    private final ClientRepository clientRepository;
    private final CreditAccountRepository creditAccountRepository;
    private final DebitAccountRepository debitAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;
    private final PersonalDetailRepository personalDetailRepository;
    private final WorkDetailRepository workDetailRepository;
    private final TransactionRepository transactionRepository;
    private final RandomDataGenerator randomDataGenerator;
    private final BankDataFaker faker;

    public void generateBankBranchStructureToDB(int branchNumber) {
        Location location = randomDataGenerator.generateLocation();
        UUID locationId = locationRepository.save(location).getId();
        BankBranch bankBranch =
                randomDataGenerator.generateBankBranch(branchNumber, locationId);
        bankBranchRepository.save(bankBranch);
    }

    @Override
    @Transactional
    public void generateBankBranchesStructureToDB(int quantity,
                                                  int startingBranchNumber) {
        Location location;
        UUID locationId;
        BankBranch bankBranch;
        for (int i = 0; i < quantity; i++) {
            location = randomDataGenerator.generateLocation();
            locationId = locationRepository.save(location).getId();
            bankBranch = randomDataGenerator.generateBankBranch(i +
                    startingBranchNumber, locationId);
            bankBranchRepository.save(bankBranch);
        }
    }

    @Override
    @Transactional
    public void generateBankInfoToDB() {
        BankInfo bankInfo = randomDataGenerator.generateBankInfo();
        bankInfoRepository.save(bankInfo);
    }


    @Override
    public void deleteBankInfoFromDB() {
        bankInfoRepository.deleteAll();
    }

    @Override
    public void deleteAllBankBranchesFromDB() {
        bankBranchRepository.deleteAll();
    }

    @Override
    public void deleteAllClientsFromDB() {
        clientRepository.deleteAll();
    }

    @Override
    public void deleteAllCreditAccountsFromDB() {
        creditAccountRepository.deleteAll();
    }

    @Override
    public void deleteAllDebitAccountsFromDB() {
        debitAccountRepository.deleteAll();
    }

    public void generateEmployeeToDB() {

    }

    @Override
    public void deleteAllEmployeesFromDB() {
        employeeRepository.deleteAll();
    }

    @Override
    public void generateLocationToDB() {
        Location location = randomDataGenerator.generateLocation();
        locationRepository.save(location);
    }

    @Override
    public void generateLocationsToDB(int quantity) {
        List<Location> locations = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            locations.add(randomDataGenerator.generateLocation());
        }
        locationRepository.saveAll(locations);
    }

    @Override
    public void deleteAllLocationsFromDB() {
        locationRepository.deleteAll();
    }

    @Override
    public void generatePersonalDetailToDB() {
        PersonalDetail personalDetail = randomDataGenerator.generatePersonalDetail();
        personalDetailRepository.save(personalDetail);
    }

    @Override
    public void generatePersonalDetailsToDB(int quantity) {
        List<PersonalDetail> personalDetails = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            personalDetails.add(randomDataGenerator.generatePersonalDetail());
        }
        personalDetailRepository.saveAll(personalDetails);
    }

    @Override
    public void deleteAllPersonalDetailsFromDB() {
        personalDetailRepository.deleteAll();
    }

    @Override
    @Transactional
    public void generateTransactionToDB() {
        Transaction transaction = randomDataGenerator.generateTransaction();
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void generateTransactionsToDB(int quantity) {
        List<Transaction> transactions = new LinkedList();
        for (int i = 0; i < quantity; i++) {
            transactions.add(randomDataGenerator.generateTransaction());
        }
        transactionRepository.saveAll(transactions);
    }

    @Override
    public void deleteAllTransactionsFromDB() {
        transactionRepository.deleteAll();
    }

    @Override
    @Transactional
    public void generateWorkDetailToDB() {
        WorkDetail workDetail = randomDataGenerator.generateWorkDetail();
        workDetailRepository.save(workDetail);
    }

    @Override
    @Transactional
    public void generateWorkDetailsToDB(int quantity) {
        List<WorkDetail> workDetails = new LinkedList();
        for (int i = 0; i < quantity; i++) {
            workDetails.add(randomDataGenerator.generateWorkDetail());
        }
        workDetailRepository.saveAll(workDetails);
    }

    @Override
    public void deleteAllWorkDetailsFromDB() {
        workDetailRepository.deleteAll();
    }

    @Override
    public ResponseEntity<byte[]> getDBSchema() {
        try {
            Path imagePath = Path.of("src/main/resources/static/images" +
                    "/DeutscheBankDB.png");
            byte[] imageBytes = Files.readAllBytes(imagePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(imageBytes.length);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }

    @Override
    public ResponseEntity<byte[]> getDbRawSchema() {
        try {
            Path imagePath = Path.of("src/main/resources/static" +
                    "/images/DeutscheBankDB.png");
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return ResponseEntity.status(HttpStatus.OK).body(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }

    @Override
    public ResponseEntity<byte[]> getLogo() {
        try {
            Path imagePath = Path.of("src/main/resources/static" +
                    "/images/banklogo.png");
            byte[] imageBytes = Files.readAllBytes(imagePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(imageBytes.length);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }

    @Override
    public ResponseEntity<byte[]> getRawLogo() {
        try {
            Path imagePath = Path.of("src/main/resources/static" +
                    "/images/banklogo.png");
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return ResponseEntity.status(HttpStatus.OK).body(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }

    public ResponseEntity<String> runTest() {
        //String result = faker.date().birthday(18, 90).toString();

        // Create a custom clock with a fixed time
        Instant customInstant = Instant.parse("2003-09-17T12:00:00Z");
        Clock customClock = Clock.fixed(customInstant, ZoneId.systemDefault());
        return ResponseEntity.status(HttpStatus.OK).body(customClock.toString());
    }
}
