package com.example.deutschebank.dto.client;

import lombok.Data;
import java.util.UUID;

@Data
public class UpdateClientDTO {
    private UUID id;
    private UUID managerId;
    private UUID debitAccountId;
    private UUID personalDetailId;
    private UUID locationId;
}
