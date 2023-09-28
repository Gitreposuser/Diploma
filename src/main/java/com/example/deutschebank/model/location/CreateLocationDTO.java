package com.example.deutschebank.model.location;

import lombok.Data;

@Data
public class CreateLocationDTO {
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private Boolean active;
}
