package com.example.deutschebank;

import io.swagger.v3.oas.models.examples.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:constantparameters.properties"),
        @PropertySource("classpath:bankaccount.properties"),
        @PropertySource("classpath:email.properties"),
        // Windows path version
        //@PropertySource("file:/media/vlad/E04DC534F7B9CE7C/BackupFiles/Programming/MailKey" +
        //"/emailslog.properties")
        @PropertySource("file:/home/vlad/IdeaProjects/MailKey/emailslog.properties")
})
public class DeutschebankApplication {
    public static void main(String[] args) {
        log.info("Starting application");
        SpringApplication.run(DeutschebankApplication.class, args);
        log.info("Example log from {}", Example.class.getSimpleName());
    }
}