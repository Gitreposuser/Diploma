package com.example.deutschebank.controller;

import com.example.deutschebank.dto.additionaldto.email.EmailDTO;
import com.example.deutschebank.dto.additionaldto.email.EmailWithAttachmentDTO;
import com.example.deutschebank.service.interfaces.additionaltools.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/email")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    @PostMapping(value = "/send")
    public void sendEmail(@RequestBody EmailDTO emailDTO) {
        emailSenderService.sendEmail(emailDTO);
    }

    @PostMapping(value = "/send/with-attachment")
    public void sendEmailWithAttachment(@RequestBody EmailWithAttachmentDTO
                                                emailWithAttachmentDTO)
            throws MessagingException {
        emailSenderService.sendEmailWithAttachment(emailWithAttachmentDTO);
    }

    @PostMapping(value = "/send/with-URL-attachment")
    public void sendEmailWithURLAttachment(@RequestBody EmailWithAttachmentDTO
                                                emailWithAttachmentDTO)
            throws MessagingException {
        emailSenderService.sendEmailWithURLAttachment(emailWithAttachmentDTO);
    }
}