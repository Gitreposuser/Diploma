package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.converter.*;
import com.example.deutschebank.dto.additional.tools.CreateDatabaseDTO;
import com.example.deutschebank.dto.bankbranch.CreateBankBranchDTO;
import com.example.deutschebank.dto.bankinfo.CreateBankInfoDTO;
import com.example.deutschebank.dto.creditaccount.CreateCreditAccountDTO;
import com.example.deutschebank.dto.debitaccount.CreateDebitAccountDTO;
import com.example.deutschebank.dto.employee.CreateEmployeeDTO;
import com.example.deutschebank.dto.location.CreateLocationDTO;
import com.example.deutschebank.dto.personaldetail.CreatePersonalDetailDTO;
import com.example.deutschebank.dto.workdetail.CreateWorkDetailDTO;
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
import org.hibernate.jdbc.Work;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ToolsServiceImpl implements ToolsService {
    private final BankBranchRepository bankBranchRepository;
    private final BankBranchService bankBranchService;
    private final BankInfoDTOConverter bankInfoDTOConverter;
    private final BankInfoService bankInfoService;
    private final BankInfoRepository bankInfoRepository;
    private final ClientRepository clientRepository;
    private final CreditAccountRepository creditAccountRepository;
    private final DebitAccountRepository debitAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;
    private final LocationService locationService;
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
        //locationService.createMultipleLocations(locations);

        List<BankBranch> bankBranches = randomDataGenerator.
                generateMultipleBankBranches(branchesQuantity, branchesLocations);
        bankBranchRepository.saveAll(bankBranches);

        // Generate employees
        int objectsQuantity = createDatabaseDTO.getEmployeesQuantity();
        List<Employee> employees = new LinkedList<>();
        List<PersonalDetail> personalDetails =
                randomDataGenerator.generateMultiplePersonalDetails(objectsQuantity);
        personalDetailRepository.saveAll(personalDetails);

        List<WorkDetail> workDetails =
                randomDataGenerator.generateMultipleWorkDetails(objectsQuantity);
        workDetailRepository.saveAll(workDetails);

        List<Location> employeeLocations =
                randomDataGenerator.generateMultipleLocations(objectsQuantity);
        locationRepository.saveAll(employeeLocations);

        employees =
                randomDataGenerator.generateMultipleEmployees(objectsQuantity,
                        personalDetails, workDetails,
                        employeeLocations, bankBranches);
        employeeRepository.saveAll(employees);

        log.info("Generate database.");
    }

    /*
    @Override
    @Transactional
    public void generateBankBranchStructureToDB(Integer branchNumber,
                                                Location location) {
        BankBranch bankBranch =
                randomDataGenerator.generateBankBranch(branchNumber, location);
        bankBranchRepository.save(bankBranch);
        log.info("Generate bank branch structure. " + bankBranch);
    }

    @Override
    @Transactional
    public void generateBankBranchesStructureToDB(Integer quantity,
                                                  Location location) {
        List<BankBranch> branches = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            BankBranch bankBranch =
                    randomDataGenerator.generateBankBranch(i + 1, location);
            branches.add(bankBranch);
        }
        bankBranchRepository.saveAll(branches);
        log.info("Generate bank branches structure, quantity: " + quantity);
    }


     */
    @Override
    @Transactional
    public void deleteAllBankBranchesFromDB() {
        bankBranchRepository.deleteAll();
        log.warn("Delete all bank branches!");
    }

    /*
    @Override
    @Transactional
    public void generateBankInfoToDB() {
        BankInfo bankInfo = randomDataGenerator.generateBankInfo();
        bankInfoRepository.save(bankInfo);
        log.info("Generate bank info.");
    }
     */

    @Override
    @Transactional
    public void deleteBankInfoFromDB() {
        bankInfoRepository.deleteAll();
        log.warn("Delete bank info!");
    }


/*
    @Override
    @Transactional
    public Client generateClientStructureToDB(Employee employee) {
        DebitAccount debitAccount = randomDataGenerator.generateDebitAccount();
        PersonalDetail personalDetail =
                randomDataGenerator.generatePersonalDetail();
        Location location = randomDataGenerator.generateLocation();
        UUID location = locationRepository.save(location).getId();
        Client client = randomDataGenerator.generateClient(employee,
                debitAccount,
                personalDetail,
                location);
        UUID clientId = clientRepository.save(client).getId();
        return clientId;
    }

    @Override
    @Transactional
    public List<UUID> generateClientsStructureToDB(int quantity,
                                                   List<UUID> managersId) {
        List<UUID> clientsId = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            UUID managerId = randomDataGenerator.chooseFromList(managersId);
            clientsId.add(generateClientStructureToDB(managerId));
        }
        return clientsId;
    }
*/
    @Override
    @Transactional
    public void deleteAllClientsFromDB() {
        clientRepository.deleteAll();
        log.warn("Delete all clients!");
    }
/*
    @Override
    @Transactional
    public void generateCreditAccountToDB(Client client) {
        CreateCreditAccountDTO creditAccount =
                randomDataGenerator.generateCreditAccount(client);
        creditAccountRepository.save(creditAccount);
    }

    @Override
    @Transactional
    public void generateCreditAccountsToDB(int quantity, List<UUID> clientsId) {
        for (int i = 0; i < quantity; i++) {
            UUID clientId = randomDataGenerator.chooseFromList(clientsId);
            generateCreditAccountToDB(clientId);
        }
    }

 */
    @Override
    @Transactional
    public void deleteAllCreditAccountsFromDB() {
        creditAccountRepository.deleteAll();
        log.warn("Delete all credit accounts!");
    }

    /*
    @Override
    @Transactional
    public void generateDebitAccountToDB() {
        DebitAccount debitAccount = randomDataGenerator.generateDebitAccount();
        debitAccountRepository.save(debitAccount);
        log.info("Generate bank branch structure.");
    }

     */

    @Override
    @Transactional
    public void deleteAllDebitAccountsFromDB() {
        debitAccountRepository.deleteAll();
        log.warn("Delete all debit accounts!");
    }

        /*
    @Override
    @Transactional
    public void generateEmployeeStructureToDB() {
        PersonalDetail personalDetail =
                randomDataGenerator.generatePersonalDetail();
        WorkDetail workDetail = randomDataGenerator.generateWorkDetail();
        Location location = randomDataGenerator.generateLocation();
        int branchNumber = 1;
        BankBranch bankBranch =
                randomDataGenerator.generateBankBranch(branchNumber);
        Employee employee =
                randomDataGenerator.generateEmployee(personalDetail,
                        workDetail, location, bankBranch);
        employeeRepository.save(employee);
        log.info("Generate employee structure.");
    }
         */

    /*
    @Override
    @Transactional
    public void generateEmployeesStructureToDB(Integer quantity) {
        List<CreateEmployeeDTO> employees = new LinkedList<>();
    }

     */

    @Override
    @Transactional
    public void deleteAllEmployeesFromDB() {
        employeeRepository.deleteAll();
        log.warn("Delete all employee!");
    }
/*
    @Override
    @Transactional
    public void generateLocationToDB() {
        Location location = randomDataGenerator.generateLocation();
        locationRepository.save(location);
        log.info("Generate location.");
    }


    @Override
    @Transactional
    public void generateLocationsToDB(Integer quantity) {
        List<Location> locations = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            Location location = randomDataGenerator.generateLocation();
            locations.add(location);
        }
        locationRepository.saveAll(locations);
    }
 */

    @Override
    @Transactional
    public void deleteAllLocationsFromDB() {
        locationRepository.deleteAll();
        log.warn("Delete all locations!");
    }
/*
    @Override
    @Transactional
    public void generatePersonalDetailToDB() {
        PersonalDetail personalDetail = randomDataGenerator
                .generatePersonalDetail();
        personalDetailRepository.save(personalDetail);
        log.info("Generate personal detail.");
    }

    @Override
    @Transactional
    public void generatePersonalDetailsToDB(Integer quantity) {
        List<PersonalDetail> personalDetails = new LinkedList();
        for (int i = 0; i < quantity; i++) {
            PersonalDetail personalDetail = randomDataGenerator
                    .generatePersonalDetail();
            personalDetails.add(personalDetail);
        }
        personalDetailRepository.saveAll(personalDetails);
    }

 */

    @Override
    @Transactional
    public void deleteAllPersonalDetailsFromDB() {
        personalDetailRepository.deleteAll();
        log.warn("Delete all personal details!");
    }
/*
    @Override
    @Transactional
    public void generateTransactionsToDB(int quantity,
                                         List<UUID> clientsId) {
        for (int i = 0; i < quantity; i++) {
            UUID randomId = randomDataGenerator.chooseFromList(clientsId);
            BigDecimal balance = clientRepository.getReferenceById(randomId)
                    .getDebitAccount()
                    .getBalance();
        }
    }
 */

    @Override
    @Transactional
    public void deleteAllTransactionsFromDB() {
        transactionRepository.deleteAll();
        log.warn("Delete all transactions!");
    }

/*
    @Override
    @Transactional
    public void generateWorkDetailToDB() {
        WorkDetail workDetail = randomDataGenerator.generateWorkDetail();
        workDetailRepository.save(workDetail);
        log.info("Generate work detail to database.");
    }

    @Override
    @Transactional
    public void generateWorkDetailsToDB(Integer quantity) {
        List<WorkDetail> workDetails = new LinkedList();
        for (int i = 0; i < quantity; i++) {
            WorkDetail workDetail = randomDataGenerator.generateWorkDetail();
            workDetails.add(workDetail);
        }
        workDetailRepository.saveAll(workDetails);
    }

 */

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
