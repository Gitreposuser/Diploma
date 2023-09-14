package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.Client;
import com.example.deutschebank.model.client.CreateClientDTO;
import com.example.deutschebank.model.client.GetClientDTO;
import com.example.deutschebank.model.client.UpdateClientDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    void createClient(CreateClientDTO createDTO);

    GetClientDTO getClientById(UUID uuid);

    List<GetClientDTO> getAllClients();

    void updateClientById(UpdateClientDTO updateDTO);

    void deleteClientById(UUID uuid);
}
