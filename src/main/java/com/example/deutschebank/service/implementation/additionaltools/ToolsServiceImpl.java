package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.converter.*;
import com.example.deutschebank.dto.additional.tools.CreateDatabaseDTO;
import com.example.deutschebank.dto.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.entity.*;
import com.example.deutschebank.repository.*;
import com.example.deutschebank.service.interfaces.BankBranchService;
import com.example.deutschebank.service.interfaces.BankInfoService;
import com.example.deutschebank.service.interfaces.LocationService;
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
    private final BankInfoService bankInfoService;
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

    @Override

    public void generateDataBase(CreateDatabaseDTO createDatabaseDTO) {
        // Generate bank info
        BankInfo bankInfo = randomDataGenerator.generateBankInfo();
        CreateBankInfoDTO createDTO =
                bankInfoDTOConverter.convertBankInfoToCreateDTO(bankInfo);
        bankInfoService.createBankInfo(createDTO);

        // Generate branches
        int branchesQuantity = createDatabaseDTO.getBranchesQuantity();
        List<Location> branchesLocations = randomDataGenerator
                .generateMultipleLocations(branchesQuantity);
        locationRepository.saveAll(branchesLocations);

        List<BankBranch> bankBranches = randomDataGenerator.
                generateMultipleBankBranches(branchesQuantity, branchesLocations);
        bankBranchRepository.saveAll(bankBranches);

        // Generate employees
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

        // Generate clients
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

        // Generate credits
        int creditAccountsQuantity = createDatabaseDTO.getCreditsQuantity();
        List<CreditAccount> creditAccounts = randomDataGenerator
                .generateMultipleCreditAccounts(creditAccountsQuantity,
                        clients);
        creditAccountRepository.saveAll(creditAccounts);

        // Generate transactions
        List<Transaction> transactions = randomDataGenerator
                .generateMultipleTransactions(createDatabaseDTO.getTransactionsQuantity(),
                        clients, clients);
        transactionRepository.saveAll(transactions);

        log.info("Generate database.");
    }

    @Override
    @Transactional
    public void deleteAllBankBranchesFromDB() {
        bankBranchRepository.deleteAll();
        log.warn("Delete all bank branches!");
    }

    @Override
    @Transactional
    public void deleteBankInfoFromDB() {
        bankInfoRepository.deleteAll();
        log.warn("Delete bank info!");
    }

    @Override
    @Transactional
    public void deleteAllClientsFromDB() {
        clientRepository.deleteAll();
        log.warn("Delete all clients!");
    }

    @Override
    @Transactional
    public void deleteAllCreditAccountsFromDB() {
        creditAccountRepository.deleteAll();
        log.warn("Delete all credit accounts!");
    }

    @Override
    @Transactional
    public void deleteAllDebitAccountsFromDB() {
        debitAccountRepository.deleteAll();
        log.warn("Delete all debit accounts!");
    }

    @Override
    @Transactional
    public void deleteAllEmployeesFromDB() {
        employeeRepository.deleteAll();
        log.warn("Delete all employee!");
    }

    @Override
    @Transactional
    public void deleteAllLocationsFromDB() {
        locationRepository.deleteAll();
        log.warn("Delete all locations!");
    }

    @Override
    @Transactional
    public void deleteAllPersonalDetailsFromDB() {
        personalDetailRepository.deleteAll();
        log.warn("Delete all personal details!");
    }

    @Override
    @Transactional
    public void deleteAllTransactionsFromDB() {
        transactionRepository.deleteAll();
        log.warn("Delete all transactions!");
    }

    @Override
    @Transactional
    public void deleteAllWorkDetailsFromDB() {
        workDetailRepository.deleteAll();
        log.warn("Delete all work details!");
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
