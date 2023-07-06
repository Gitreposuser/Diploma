package com.example.deutschebank.service.implementation;

import com.example.deutschebank.entity.Client;
import com.example.deutschebank.repository.ClientRepository;
import com.example.deutschebank.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void createClient(Client client) {
        //clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClientById(UUID id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public void deleteClient(Client client) {

    }

    @Override
    public void deleteClientById(UUID id) {

    }
}