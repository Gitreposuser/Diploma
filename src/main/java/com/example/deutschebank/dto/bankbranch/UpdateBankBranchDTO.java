package com.example.deutschebank.dto.bankbranch;

import com.example.deutschebank.entity.Location;
import com.example.deutschebank.entity.enums.BranchStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateBankBranchDTO {
    @NotNull(message = "Id should not be null!")
    private UUID id;

    @NotNull(message = "Branch number should not be null!")
    @Min(value = 1, message = "Branch number should start from 1!")
    private Integer branchNumber;

    @NotNull(message = "Branch status should not be null!")
    private BranchStatus branchStatus;

    @NotNull(message = "Location should not be null!")
    private Location location;

    @NotBlank(message = "General phone should not be empty!")
    @NotNull(message = "General phone should not be null!")
    @Size(min = 9, max = 18, message = "General phone should be in range " +
            "from 9 to 18 digits!")
    @Pattern(regexp = "^(?:[0-9]+[-. ()]*)+$",
            message = "Invalid general phone number!")
    private String generalPhone;

    @NotBlank(message = "Hot line should not be empty!")
    @NotNull(message = "Hot line should not be null!")
    @Size(min = 9, max = 18, message = "Hot line should be in range " +
            "from 9 to 18 digits!")
    @Pattern(regexp = "^(?:[0-9]+[-. ()]*)+$",
            message = "Invalid hot line number!")
    private String hotLine;

    @NotNull(message = "Active should not be null!")
    @AssertTrue(message = "Active is wrong!")
    private Boolean active;
}