package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.client.*;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    void createClient(CreateClientDTO createDTO);

    GetClientDTO getClientById(UUID uuid);

    GetClientDTO getClientByFullName(String fullName);

    List<GetClientDTO> getAllActiveClients();

    List<GetClientDTO> getAllClients();

    void updateClientById(UpdateClientDTO updateDTO);

    void markAsDeletedClientById(UUID uuid);

    GetClientIbanDTO getClientIbanByFullName(String fullName);

    GetClientInfoDTO getClientInfoByFullName(String fullName);
}
