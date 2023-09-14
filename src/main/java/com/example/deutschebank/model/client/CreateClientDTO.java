package com.example.deutschebank.model.client;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateClientDTO {
    public UUID managerId;
    public UUID debitAccountId;
    public UUID personalDetailId;
    public UUID locationId;
    public Boolean active;
}
