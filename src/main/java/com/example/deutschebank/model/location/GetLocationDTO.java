package com.example.deutschebank.model.location;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetLocationDTO {
    public UUID id;
    public Integer houseNumber;
    public String street;
    public String city;
    public String state;
    public String country;
    public LocalDateTime created;
    public LocalDateTime updated;
}
