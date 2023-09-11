package com.example.deutschebank.model.location;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateLocationDTO {
    public UUID id;
    public Integer houseNumber;
    public String street;
    public String city;
    public String state;
    public String country;
}
