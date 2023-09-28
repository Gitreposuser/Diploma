package com.example.deutschebank.model.personaldetail;

import com.example.deutschebank.entity.enums.Sex;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePersonalDetailDTO {
    private String firstName;
    private String lastName;
    private Sex sex;
    private String phone;
    private String email;
    private Integer age;
    private LocalDateTime birthDate;
    private Boolean isMarried;
    private Integer children;
    private Boolean active;
}
