package com.example.deutschebank.dto.personaldetail;

import com.example.deutschebank.entity.enums.Sex;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetPersonalDetailDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private Sex sex;
    private String phone;
    private String email;
    private Integer age;
    private LocalDateTime birthDate;
    private Boolean isMarried;
    private Integer children;
    private LocalDateTime created;
    private LocalDateTime updated;
}
