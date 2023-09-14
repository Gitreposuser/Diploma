package com.example.deutschebank.config;

import com.example.deutschebank.service.implementation.additionaltools.BankFaker;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FakerConfig {
    @Bean
    public BankFaker modelFaker() {
        return new BankFaker();
    }
}