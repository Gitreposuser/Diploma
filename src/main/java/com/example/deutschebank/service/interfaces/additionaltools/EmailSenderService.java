package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.dto.additional.email.EmailDTO;
import com.example.deutschebank.dto.additional.email.EmailWithAttachmentDTO;

public interface EmailSenderService {
    void sendEmail(EmailDTO emailDTO);

    void sendEmailWithAttachment(EmailWithAttachmentDTO emailAttachmentDTO);

    void sendEmailWithURLAttachment(EmailWithAttachmentDTO emailAttachmentDTO);
}
