package com.example.deutschebank.dto.location;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetLocationDTO {
    private UUID id;
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private Boolean active;
    private LocalDateTime created;
    private LocalDateTime updated;
}
