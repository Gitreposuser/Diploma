package com.example.deutschebank.dto.workdetail;

import com.example.deutschebank.entity.enums.WorkStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateWorkDetailDTO {
    @NotNull(message = "Id should not be empty!")
    private UUID id;

    @NotNull(message = "Position should not be null!")
    @NotBlank(message = "Position should not be empty!")
    private String position;

    @NotNull(message = "Work status should not be null!")
    private WorkStatus workStatus;

    @NotNull(message = "Salary should not be null!")
    @Min(value = 1000, message = "Salary should start from 1000!")
    private BigDecimal salary;

    @NotNull(message = "Work phone should not be null!")
    @NotBlank(message = "Work phone should not be empty!")
    @Size(min = 9, max = 18, message = "Work phone should be in range " +
            "from 9 to 18 digits!")
    @Pattern(regexp = "^(?:[0-9]+[-. ()]*)+$",
            message = "Invalid work phone number!")
    private String workPhone;

    @NotNull(message = "Work email should not be null!")
    @Email(message = "Wrong work email format!")
    private String workEmail;

    @NotNull(message = "Start from date should not be null!")
    @Future(message = "Start from date must be in future!")
    private LocalDateTime startFrom;

    private LocalDateTime endAt;
}