package com.example.deutschebank.controller;

import com.example.deutschebank.service.interfaces.TransactionService;
import com.example.deutschebank.service.interfaces.additionaltools.RandomDataGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tools")
public class ToolsController {
    private final RandomDataGenerator randomDataGenerator;

    @GetMapping("/generate-db")
    public void generateDataBase() {
        final int transactionQuantity = 1000;   // Create body with post!
        randomDataGenerator.generateTransactions(transactionQuantity);
    }

    @GetMapping("/schema")
    public ResponseEntity<byte[]> getDbSchema() {
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

    @GetMapping("/raw-schema")
    public ResponseEntity<byte[]> test() {
        try {
            Path imagePath = Path.of("src/main/resources/static/images" +
                    "/DeutscheBankDB.png");
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return ResponseEntity.status(HttpStatus.OK).body(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }

    @GetMapping("/logo")
    public ResponseEntity<byte[]> getLogo() {
        try {
            Path imagePath = Path.of("E:/Programming/Diploma/deutschebank" +
                    "/src/main/resources/static/images/banklogo.png");

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
}