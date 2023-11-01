package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.converter.*;
import com.example.deutschebank.dto.additional.tools.CreateDatabaseDTO;
import com.example.deutschebank.dto.bankaccount.CreateBankAccountDTO;
import com.example.deutschebank.entity.*;
import com.example.deutschebank.repository.*;
import com.example.deutschebank.service.interfaces.BankAccountService;
import com.example.deutschebank.service.interfaces.additionaltools.ToolsService;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGeneratorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ToolsServiceImpl implements ToolsService {
    private final BankBranchRepository bankBranchRepository;
    private final BankInfoDTOConverter bankInfoDTOConverter;
    private final BankAccountService bankAccountService;
    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final CreditAccountRepository creditAccountRepository;
    private final DebitAccountRepository debitAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;
    private final PersonalDetailRepository personalDetailRepository;
    private final WorkDetailRepository workDetailRepository;
    private final TransactionRepository transactionRepository;
    private final RandomDataGeneratorService randomDataGenerator;

    @Override
    @Transactional
    public void generateDataBase(CreateDatabaseDTO createDatabaseDTO) {
        // Generate bank info
        generateBankInfo();

        // Generate branches
        List<BankBranch> bankBranches = generateBankBranches(createDatabaseDTO);

        // Generate employees
        List<Employee> employees = generateEmployees(createDatabaseDTO, bankBranches);

        // Generate clients
        List<Client> clients = generateClients(createDatabaseDTO, employees);

        // Generate credits
        generateCreditAccounts(createDatabaseDTO, clients);

        // Generate transactions
        generateTransactions(createDatabaseDTO, clients);

        log.info("Generate database.");
    }

    @Transactional
    private void generateBankInfo(){
        BankAccount bankAccount = randomDataGenerator.generateBankInfo();
        CreateBankAccountDTO createDTO =
                bankInfoDTOConverter.convertBankInfoToCreateDTO(bankAccount);
        bankAccountService.createBankInfo(createDTO);
    }

    @Transactional
    private List<BankBranch> generateBankBranches(CreateDatabaseDTO createDatabaseDTO) {
        int branchesQuantity = createDatabaseDTO.getBranchesQuantity();
        List<Location> branchesLocations = randomDataGenerator
                .generateMultipleLocations(branchesQuantity);
        locationRepository.saveAll(branchesLocations);

        List<BankBranch> bankBranches = randomDataGenerator.
                generateMultipleBankBranches(branchesQuantity, branchesLocations);
        bankBranchRepository.saveAll(bankBranches);
        return bankBranches;
    }

    @Transactional
    private List<Employee> generateEmployees(CreateDatabaseDTO createDatabaseDTO,
                                             List<BankBranch> bankBranches) {
        int employeesQuantity = createDatabaseDTO.getEmployeesQuantity();
        List<PersonalDetail> employeePersonalDetails =
                randomDataGenerator.generateMultiplePersonalDetails(employeesQuantity);
        personalDetailRepository.saveAll(employeePersonalDetails);

        List<WorkDetail> employeeWorkDetails =
                randomDataGenerator.generateMultipleWorkDetails(employeesQuantity);
        workDetailRepository.saveAll(employeeWorkDetails);

        List<Location> employeeLocations =
                randomDataGenerator.generateMultipleLocations(employeesQuantity);
        locationRepository.saveAll(employeeLocations);

        List<Employee> employees =
                randomDataGenerator.generateMultipleEmployees(employeesQuantity,
                        employeePersonalDetails, employeeWorkDetails,
                        employeeLocations, bankBranches);
        employeeRepository.saveAll(employees);
        return employees;
    }

    @Transactional
    private List<Client> generateClients(CreateDatabaseDTO createDatabaseDTO,
                                         List<Employee> employees) {
        int clientsQuantity = createDatabaseDTO.getClientsQuantity();
        List<PersonalDetail> clientPersonalDetails = randomDataGenerator
                .generateMultiplePersonalDetails(clientsQuantity);
        personalDetailRepository.saveAll(clientPersonalDetails);

        List<DebitAccount> clientDebitAccounts = randomDataGenerator
                .generateMultipleDebitAccounts(clientsQuantity);
        debitAccountRepository.saveAll(clientDebitAccounts);

        List<Location> clientLocations = randomDataGenerator
                .generateMultipleLocations(clientsQuantity);
        locationRepository.saveAll(clientLocations);

        List<Client> clients = randomDataGenerator
                .generateMultipleClients(clientsQuantity,
                        clientPersonalDetails, clientDebitAccounts,
                        clientLocations, employees);
        clientRepository.saveAll(clients);
        return clients;
    }

    @Transactional
    private void generateCreditAccounts(CreateDatabaseDTO createDatabaseDTO,
                                                       List<Client> clients) {
        int creditAccountsQuantity = createDatabaseDTO.getCreditsQuantity();
        List<CreditAccount> creditAccounts = randomDataGenerator
                .generateMultipleCreditAccounts(creditAccountsQuantity,
                        clients);
        creditAccountRepository.saveAll(creditAccounts);
    }

    @Transactional
    private void generateTransactions(CreateDatabaseDTO createDatabaseDTO,
                                      List<Client> clients) {
        List<Transaction> transactions = randomDataGenerator
                .generateMultipleTransactions(createDatabaseDTO.getTransactionsQuantity(),
                        clients, clients);
        transactionRepository.saveAll(transactions);
    }

    @Override
    @Transactional
    public void deleteAllBankBranchesFromDB() {
        bankBranchRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteBankInfoFromDB() {
        bankAccountRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllClientsFromDB() {
        clientRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllCreditAccountsFromDB() {
        creditAccountRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllDebitAccountsFromDB() {
        debitAccountRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllEmployeesFromDB() {
        employeeRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllLocationsFromDB() {
        locationRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllPersonalDetailsFromDB() {
        personalDetailRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllTransactionsFromDB() {
        transactionRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllWorkDetailsFromDB() {
        workDetailRepository.deleteAll();
    }

    @Override
    @Transactional
    public ResponseEntity<byte[]> getDBSchema() {
        log.info("Get database schema.");
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
    @Transactional
    public ResponseEntity<byte[]> getDbRawSchema() {
        log.info("Get database raw schema.");
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
    @Transactional
    public ResponseEntity<byte[]> getLogo() {
        log.info("Get logo.");
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
    @Transactional
    public ResponseEntity<byte[]> getRawLogo() {
        log.info("Get raw logo.");
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

    }
}
