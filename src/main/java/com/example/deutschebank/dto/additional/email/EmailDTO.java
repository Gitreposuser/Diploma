package com.example.deutschebank.dto.additional.email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailDTO {
    @NotNull(message = "Email to could not be null!")
    private String[] emailTo;
    private String[] cc;
    private String[] bcc;

    @NotNull(message = "Subject could not be null!")
    @NotBlank(message = "Subject could not be empty!")
    private String subject;

    @NotNull(message = "Text could not be null!")
    @NotBlank(message = "Text could not be empty!")
    private String text;
}