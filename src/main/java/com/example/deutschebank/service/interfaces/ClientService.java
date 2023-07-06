package com.example.deutschebank.service.interfaces;

import com.example.deutschebank.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    void createClient(Client client);

    Optional<Client> getClientById(UUID id);

    void deleteClient(Client client);

    void deleteClientById(UUID id);
}
