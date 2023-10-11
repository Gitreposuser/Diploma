package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.converter.BankBranchDTOConverter;
import com.example.deutschebank.entity.*;
import com.example.deutschebank.dto.bankbranch.GetBranchCityDTO;
import com.example.deutschebank.repository.*;
import com.example.deutschebank.service.interfaces.additionaltools.EmailSenderService;
import com.example.deutschebank.service.interfaces.additionaltools.ToolsService;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGeneratorService;
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
    private final RandomDataGeneratorService randomDataGenerator;
    private final EmailSenderService emailSenderService;

    //
    // Debug
    //
    private final BankBranchDTOConverter bankBranchDTOConverter;

    public GetBranchCityDTO getBranchCity(UUID uuid) {
        BankBranch bankBranch = bankBranchRepository.findById(uuid).get();
        return bankBranchDTOConverter.convertBankBranchToCityDTO(bankBranch);
    }

    @Override
    @Transactional
    public UUID generateBankBranchStructureToDB(int branchNumber) {
        Location location = randomDataGenerator.generateLocation();
        UUID locationId = locationRepository.save(location).getId();
        BankBranch bankBranch =
                randomDataGenerator.generateBankBranch(branchNumber, locationId);
        UUID branchId = bankBranchRepository.save(bankBranch).getId();
        return branchId;
    }

    @Override
    @Transactional
    public List<UUID> generateBankBranchesStructureToDB(int quantity) {
        List<UUID> branchesId = new LinkedList<>();
        Location location;
        UUID locationId;
        BankBranch bankBranch;
        for (int i = 0; i < quantity; i++) {
            location = randomDataGenerator.generateLocation();
            locationId = locationRepository.save(location).getId();
            bankBranch = randomDataGenerator.generateBankBranch(i + 1,
                    locationId);
            branchesId.add(bankBranchRepository.save(bankBranch).getId());
        }
        return branchesId;
    }

    @Override
    public void deleteAllBankBranchesFromDB() {
        bankBranchRepository.deleteAll();
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
    public UUID generateClientStructureToDB(UUID managerId) {
        DebitAccount debitAccount = randomDataGenerator.generateDebitAccount();
        UUID debitAccountId = debitAccountRepository.save(debitAccount).getId();
        PersonalDetail personalDetail =
                randomDataGenerator.generatePersonalDetail();
        UUID personalDetailId =
                personalDetailRepository.save(personalDetail).getId();
        Location location = randomDataGenerator.generateLocation();
        UUID locationId = locationRepository.save(location).getId();
        Client client = randomDataGenerator.generateClient(managerId,
                debitAccountId,
                personalDetailId,
                locationId);
        UUID clientId = clientRepository.save(client).getId();
        return clientId;
    }

    @Override
    public List<UUID> generateClientsStructureToDB(int quantity,
                                                   List<UUID> managersId) {
        List<UUID> clientsId = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            UUID managerId = randomDataGenerator.chooseFromList(managersId);
            clientsId.add(generateClientStructureToDB(managerId));
        }
        return clientsId;
    }

    @Override
    public void deleteAllClientsFromDB() {
        clientRepository.deleteAll();
    }

    @Override
    public void generateCreditAccountToDB(UUID clientId) {
        CreditAccount creditAccount =
                randomDataGenerator.generateCreditAccount(clientId);
        creditAccountRepository.save(creditAccount);
    }

    @Override
    public void generateCreditAccountsToDB(int quantity, List<UUID> clientsId) {
        for (int i = 0; i < quantity; i++) {
            UUID clientId = randomDataGenerator.chooseFromList(clientsId);
            generateCreditAccountToDB(clientId);
        }
    }

    @Override
    public void deleteAllCreditAccountsFromDB() {
        creditAccountRepository.deleteAll();
    }

    @Override
    public UUID generateDebitAccountToDB() {
        DebitAccount debitAccount = randomDataGenerator.generateDebitAccount();
        UUID debitAccountId = debitAccountRepository.save(debitAccount).getId();
        return debitAccountId;
    }

    @Override
    public void deleteAllDebitAccountsFromDB() {
        debitAccountRepository.deleteAll();
    }

    @Override
    public UUID generateEmployeeStructureToDB(UUID branchId) {
        Location location = randomDataGenerator.generateLocation();
        UUID locationId = locationRepository.save(location).getId();
        PersonalDetail personalDetail =
                randomDataGenerator.generatePersonalDetail();
        UUID personalDetailId =
                personalDetailRepository.save(personalDetail).getId();
        WorkDetail workDetail = randomDataGenerator.generateWorkDetail();
        UUID workDetailId = workDetailRepository.save(workDetail).getId();
        Employee employee =
                randomDataGenerator.generateEmployee(personalDetailId,
                        workDetailId,
                        locationId,
                        branchId);
        UUID employeeId = employeeRepository.save(employee).getId();
        return employeeId;
    }

    public List<UUID> generateEmployeesStructureToDB(int quantity,
                                                     List<UUID> branchesId) {
        List<UUID> employeesId = new LinkedList();
        for (int i = 0; i < quantity; i++) {
            UUID branchId = randomDataGenerator.chooseFromList(branchesId);
            employeesId.add(generateEmployeeStructureToDB(branchId));
        }
        return employeesId;
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

    public void runTest() {
        String toEmail = "vlad77solo@gmail.com";
        //String toEmail = "alpina1904@gmail.com";
        String [] cc = {"zepagaci@tutuapp.bid"};
        String [] bcc = {"yifevi4551@hapincy.com"};
        String subject = "email sender works!";
        String body = "Congratulations! You successfully send a letter";
        emailSenderService.sendEmail(toEmail,
                cc,
                bcc,
                subject,
                body);
    }
}
