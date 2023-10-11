package com.example.deutschebank.dto.location;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateLocationDTO {
    private UUID id;
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
}
