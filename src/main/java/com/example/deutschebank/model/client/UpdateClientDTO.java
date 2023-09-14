package com.example.deutschebank.model.client;

import lombok.Data;
import java.util.UUID;

@Data
public class UpdateClientDTO {
    public UUID id;
    public UUID managerId;
    public UUID debitAccountId;
    public UUID personalDetailId;
    public UUID locationId;
}
