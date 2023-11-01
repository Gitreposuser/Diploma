package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.dto.additional.email.EmailDTO;
import com.example.deutschebank.dto.additional.email.EmailWithAttachmentDTO;
import com.example.deutschebank.exception.BadEmailException;
import com.example.deutschebank.service.interfaces.additionaltools.EmailSenderService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        String[] emailTo = emailDTO.getEmailTo();
        String[] cc = emailDTO.getCc();
        String[] bcc = emailDTO.getBcc();
        String subject = emailDTO.getSubject();
        String text = emailDTO.getText();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            if (isValid(emailTo)) {
                message.setTo(emailTo);
            }
            if (isValid(cc)) {
                message.setCc(cc);
            }
            if (isValid(bcc)) {
                message.setBcc(bcc);
            }
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);
            log.info(message.toString());
        } catch (Exception e) {
            log.error("Something went wrong while sending simple message " +
                    "email! " + e.getMessage());
            throw new BadEmailException(e.getMessage());
        }
    }

    @Override
    public void sendEmailWithAttachment(EmailWithAttachmentDTO emailAttachmentDTO) {
        String[] emailTo = emailAttachmentDTO.getEmailTo();
        String[] cc = emailAttachmentDTO.getCc();
        String[] bcc = emailAttachmentDTO.getBcc();
        String subject = emailAttachmentDTO.getSubject();
        String text = emailAttachmentDTO.getText();
        String pathToAttachment = emailAttachmentDTO.getPathToAttachment();
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            if (isValid(emailTo)) {
                helper.setTo(emailTo);
            }
            if (isValid(cc)) {
                helper.setCc(cc);
            }
            if (isValid(bcc)) {
                helper.setBcc(bcc);
            }
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file;
            file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("image.png", file);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Error while create attachment for letter" + e.getMessage());
            throw new BadEmailException(e.getMessage());
        }
    }

    @Override
    public void sendEmailWithURLAttachment(EmailWithAttachmentDTO emailAttachmentDTO) {
        String[] emailTo = emailAttachmentDTO.getEmailTo();
        String[] cc = emailAttachmentDTO.getCc();
        String[] bcc = emailAttachmentDTO.getBcc();
        String subject = emailAttachmentDTO.getSubject();
        String text = emailAttachmentDTO.getText();
        String URLToAttachment = emailAttachmentDTO.getPathToAttachment();
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            if (isValid(emailTo)) {
                helper.setTo(emailTo);
            }
            if (isValid(cc)) {
                helper.setCc(cc);
            }
            if (isValid(bcc)) {
                helper.setBcc(bcc);
            }
            helper.setSubject(subject);
            helper.setText(text);

            // Download the image from the URL
            URL url = new URL(URLToAttachment);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();

            // Read the image bytes into a byte array
            byte[] imageBytes = inputStream.readAllBytes();

            // Attach the image to the email
            helper.addAttachment("remote-image.png",
                    new ByteArrayResource(imageBytes));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Oops something went wrong, while creating mime " +
                    "message from URL! " + e.getMessage());
            throw new BadEmailException(e.getMessage());
        }
    }

    private boolean isValid(String[] array) {
        return array != null && array.length > 0;
    }
}