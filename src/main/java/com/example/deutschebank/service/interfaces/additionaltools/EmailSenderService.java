package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.dto.additionaldto.email.EmailDTO;
import com.example.deutschebank.dto.additionaldto.email.EmailWithAttachmentDTO;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailSenderService {
    void sendEmail(String emailTo, String[] cc, String[] bcc,
                   String subject, String body);

    void sendEmail(EmailDTO emailDTO);

    void sendEmailWithAttachment(String emailTo,
                                 String[] cc,
                                 String[] bcc,
                                 String subject,
                                 String body,
                                 String pathToAttachment) throws MessagingException;

    void sendEmailWithAttachment(EmailWithAttachmentDTO emailAttachmentDTO)
            throws MessagingException;

    void sendEmailWithURLAttachment(String emailTo,
                                 String[] cc,
                                 String[] bcc,
                                 String subject,
                                 String body,
                                 String pathToURLAttachment) throws MessagingException, IOException;

    void sendEmailWithURLAttachment(EmailWithAttachmentDTO emailAttachmentDTO)
            throws MessagingException;
}
