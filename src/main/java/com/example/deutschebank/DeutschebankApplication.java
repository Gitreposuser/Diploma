package com.example.deutschebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:constantparameters.properties")
public class DeutschebankApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeutschebankApplication.class, args);
    }
}