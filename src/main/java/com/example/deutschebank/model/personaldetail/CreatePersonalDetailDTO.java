package com.example.deutschebank.model.personaldetail;

import com.example.deutschebank.entity.enums.Sex;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePersonalDetailDTO {
    public String firstName;
    public String lastName;
    public Sex sex;
    public String phone;
    public String email;
    public Integer age;
    public LocalDateTime birthDate;
    public Boolean isMarried;
    public Integer childrens;
}
