package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.dto.additionaldto.email.EmailDTO;
import com.example.deutschebank.dto.additionaldto.email.EmailWithAttachmentDTO;
import com.example.deutschebank.service.interfaces.additionaltools.EmailSenderService;
import jakarta.mail.MessagingException;
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
    public void sendEmail(String emailTo,
                          String[] cc,
                          String[] bcc,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        if (cc.length > 0) {
            message.setCc(cc);
        }
        if (bcc.length > 0) {
            message.setBcc(bcc);
        }
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        log.info(message.toString());
    }

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        sendEmail(emailDTO.getEmailTo(),
                emailDTO.getCc(),
                emailDTO.getBcc(),
                emailDTO.getSubject(),
                emailDTO.getBody());
    }

    @Override
    public void sendEmailWithAttachment(String emailTo,
                                        String[] cc,
                                        String[] bcc,
                                        String subject,
                                        String body,
                                        String pathToAttachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailTo);
        if (cc.length > 0) {
            helper.setCc(cc);
        }
        if (bcc.length > 0) {
            helper.setBcc(bcc);
        }
        helper.setSubject(subject);
        helper.setText(body);

        FileSystemResource file;
        try{
            file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("image.png", file);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Error while create attachment for letter" + e);
        }
    }

    @Override
    public void sendEmailWithAttachment(EmailWithAttachmentDTO emailAttachmentDTO)
            throws MessagingException {
        sendEmailWithAttachment(emailAttachmentDTO.getEmailTo(),
                emailAttachmentDTO.getCc(),
                emailAttachmentDTO.getBcc(),
                emailAttachmentDTO.getSubject(),
                emailAttachmentDTO.getBody(),
                emailAttachmentDTO.getPathToAttachment());
    }

    @Override
    public void sendEmailWithURLAttachment(String emailTo,
                                           String[] cc,
                                           String[] bcc,
                                           String subject,
                                           String body,
                                           String URLToAttachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailTo);
        if (cc.length > 0) {
            helper.setCc(cc);
        }
        if (bcc.length > 0) {
            helper.setBcc(bcc);
        }
        helper.setSubject(subject);
        helper.setText(body);

        try {
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
            log.error("Ooups something went wrong, while creating mime " +
                    "message from URL!");
        }
    }

    @Override
    public void sendEmailWithURLAttachment(EmailWithAttachmentDTO emailAttachmentDTO)
            throws MessagingException {
        sendEmailWithURLAttachment(emailAttachmentDTO.getEmailTo(),
                emailAttachmentDTO.getCc(),
                emailAttachmentDTO.getBcc(),
                emailAttachmentDTO.getSubject(),
                emailAttachmentDTO.getBody(),
                emailAttachmentDTO.getPathToAttachment());
    }
}