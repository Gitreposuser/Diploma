package com.example.deutschebank.dto.personaldetail;

import com.example.deutschebank.entity.enums.Sex;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePersonalDetailDTO {
    @NotNull(message = "First name should not be null!")
    @NotBlank(message = "First name should not be empty!")
    private String firstName;

    @NotNull(message = "Last name should not be null!")
    @NotBlank(message = "Last name should not be empty!")
    private String lastName;

    @NotNull(message = "Sex should not be null!")
    private Sex sex;

    @NotBlank(message = "Phone number should not be empty!")
    @NotNull(message = "Phone number should not be null!")
    @Size(min = 9, max = 18, message = "Phone number should be in range " +
            "from 9 to 18 digits!")
    @Pattern(regexp = "^(?:[0-9]+[-. ()]*)+$",
            message = "Invalid hot line number!")
    private String phone;

    @Email(message = "Wrong email format!")
    private String email;

    @NotNull(message = "Age should not be null!")
    @Min(value = 12, message = "Age should be greater than 12!")
    private Integer age;

    @NotNull(message = "Birth date should not be null!")
    @Past(message = "Birth date must be in the past!")
    private LocalDateTime birthDate;

    @NotNull(message = "IsMarried should not be null!")
    private Boolean isMarried;

    @NotNull(message = "Children number should not be null!")
    @Min(value = 0, message = "Children number should be greater or equal to 0!")
    private Integer children;
}