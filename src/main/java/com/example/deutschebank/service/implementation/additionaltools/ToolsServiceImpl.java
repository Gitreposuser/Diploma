package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.entity.BankInfo;
import com.example.deutschebank.repository.BankInfoRepository;
import com.example.deutschebank.repository.TransactionRepository;
import com.example.deutschebank.service.interfaces.additionaltools.ToolsService;
import com.example.deutschebank.service.interfaces.additionaltools.ImageService;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class ToolsServiceImpl implements ToolsService {
    private final BankInfoRepository bankInfoRepository;
    private final TransactionRepository transactionRepository;
    private final RandomDataGenerator randomDataGenerator;
    private final ImageService imageService;
    private final BankFaker faker;

    @Override
    @Transactional
    public void generateBankInfoToDB() {
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

        String imagePath = "src/main/resources/static" +
                "/images/banklogo.png";
        byte[] imageBytes = imageService.convertImageToByteArray(imagePath);
        bankInfo.setLogo(imageBytes);
        bankInfoRepository.save(bankInfo);
    }

    @Override
    public void deleteBankInfoFromDB() {
        bankInfoRepository.deleteAll();
    }

    @Override
    @Transactional
    public void generateTransactionsToDB(int quantity) {
        randomDataGenerator.generateTransactionsToDB(quantity);
    }

    @Override
    public void deleteAllTransactionsFromDB() {
        transactionRepository.deleteAll();
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
}
