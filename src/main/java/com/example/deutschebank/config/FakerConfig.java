package com.example.deutschebank.config;

import com.example.deutschebank.service.implementation.additionaltools.BankDataFaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FakerConfig {
    @Bean
    public BankDataFaker modelFaker() {
        return new BankDataFaker();
    }
}