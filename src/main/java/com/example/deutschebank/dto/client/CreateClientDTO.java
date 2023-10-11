package com.example.deutschebank.dto.client;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateClientDTO {
    private UUID managerId;
    private UUID debitAccountId;
    private UUID personalDetailId;
    private UUID locationId;
    private Boolean active;
}
