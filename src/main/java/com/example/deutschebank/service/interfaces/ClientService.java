package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.dto.client.CreateClientDTO;
import com.example.deutschebank.dto.client.GetClientDTO;
import com.example.deutschebank.dto.client.UpdateClientDTO;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    void createClient(CreateClientDTO createDTO);

    GetClientDTO getClientById(UUID uuid);

    List<GetClientDTO> getAllClients();

    void updateClientById(UpdateClientDTO updateDTO);

    void deleteClientById(UUID uuid);
}
