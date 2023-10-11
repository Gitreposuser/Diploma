package com.example.deutschebank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailSenderConfig {

    @Value(value = "${spring.mail.host}")
    private String mailHost;

    @Value(value = "${spring.mail.port}")
    private int mailPort;

    @Value(value = "${spring.mail.username}")
    private String mailUsername;

    @Value(value = "${spring.mail.password}")
    private String mailPassword;

    @Value(value = "${spring.mail.smtp.type.key}")
    private String smtpTypeKey;

    @Value(value = "${spring.mail.smtp.type.value}")
    private String smtpTypeValue;

    @Value(value = "${spring.mail.smtp.auth.key}")
    private String smtpAuthKey;

    @Value(value = "${spring.mail.smtp.auth.value}")
    private String smtpAuthValue;

    @Value(value = "${spring.mail.smtp.start.key}")
    private String smtpStartKey;

    @Value(value = "${spring.mail.smtp.start.value}")
    private String getSmtpStartValue;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put(smtpTypeKey, smtpTypeValue);
        props.put(smtpAuthKey, smtpAuthValue);
        props.put(smtpStartKey, getSmtpStartValue);

        return mailSender;
    }
}
