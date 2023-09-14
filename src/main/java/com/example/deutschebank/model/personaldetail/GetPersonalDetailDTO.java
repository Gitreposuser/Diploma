package com.example.deutschebank.model.personaldetail;

import com.example.deutschebank.entity.enums.Sex;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetPersonalDetailDTO {
    public UUID id;
    public String firstName;
    public String lastName;
    public Sex sex;
    public String phone;
    public String email;
    public Integer age;
    public LocalDateTime birthDate;
    public Boolean isMarried;
    public Integer childrens;
    public Boolean active;
    public LocalDateTime created;
    public LocalDateTime updated;
}
