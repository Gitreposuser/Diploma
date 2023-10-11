package com.example.deutschebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:constantparameters.properties"),
        @PropertySource("classpath:bankinfo.properties"),
        @PropertySource("classpath:email.properties"),
        @PropertySource("file:E:/Programming/MailKey/emailslog.properties")
})
public class DeutschebankApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeutschebankApplication.class, args);
    }
}