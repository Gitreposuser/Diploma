package com.example.deutschebank.model.location;

import lombok.Data;

@Data
public class CreateLocationDTO {
    public Integer houseNumber;
    public String street;
    public String city;
    public String state;
    public String country;
}
