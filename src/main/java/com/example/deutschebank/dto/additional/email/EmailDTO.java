package com.example.deutschebank.dto.additional.email;

import lombok.Data;

@Data
public class EmailDTO {
    private String[] emailTo;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String text;
}
