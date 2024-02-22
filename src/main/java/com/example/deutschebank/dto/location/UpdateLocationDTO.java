package com.example.deutschebank.dto.location;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateLocationDTO {
    @NotNull(message = "Location id should not be null!")
    private UUID id;

    @NotNull(message = "House number should not be null!")
    @Min(value = 1, message = "House number should be greater than 0!")
    private Integer houseNumber;

    @NotNull(message = "Street name should not be null!")
    @NotBlank(message = "Street name should not be empty!")
    private String street;

    @NotNull(message = "City name should not be null!")
    @NotBlank(message = "City name should not be empty!")
    private String city;

    @NotNull(message = "State should not be null!")
    @NotBlank(message = "State should not be empty!")
    private String state;

    @NotNull(message = "Country name should not be null!")
    @NotBlank(message = "Country name should not be empty!")
    private String country;
}