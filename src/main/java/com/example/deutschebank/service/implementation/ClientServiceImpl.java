package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.ClientDTOConverter;
import com.example.deutschebank.dto.client.*;
import com.example.deutschebank.entity.Client;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.repository.ClientRepository;
import com.example.deutschebank.service.interfaces.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientDTOConverter clientDTOConverter;

    @Override
    @Transactional
    public void createClient(CreateClientDTO createDTO) {
        Client client = clientDTOConverter.convertCreateDTOToClient(createDTO);
        clientRepository.save(client);
        log.info("Client successfully created");
    }

    @Override
    @Transactional
    public GetClientDTO getClientById(UUID uuid) {
        checkIfNotExist(uuid);
        Client client = clientRepository.getReferenceById(uuid);
        log.info("Get client DTO by id: " + uuid);
        return clientDTOConverter.convertClientToGetDTO(client);
    }

    @Override
    @Transactional
    public GetClientDTO getClientByFullName(String fullName) {
        Client client = clientRepository.getClientByFullName(fullName);
        checkIfNotExist(client);
        log.info("Get client DTO by full name: " + fullName);
        return clientDTOConverter.convertClientToGetDTO(client);
    }

    @Override
    @Transactional
    public GetClientIbanDTO getClientIbanByFullName(String fullName) {
        String clientIban = clientRepository.getClientIbanByFullName(fullName);
        checkIfNotExist(clientIban);
        GetClientIbanDTO clientIbanDTO = new GetClientIbanDTO();
        clientIbanDTO.setIban(clientIban);
        log.info("Get client IBAN by full name: " + fullName);
        return clientIbanDTO;
    }

    @Override
    @Transactional
    public GetClientInfoDTO getClientInfoByFullName(String fullName) {
        Client client = clientRepository.getClientByFullName(fullName);
        checkIfNotExist(client);
        log.info("Get client info DTO by full name: " + fullName);
        return clientDTOConverter.convertClientToGetClientInfoDTO(client);
    }

    @Override
    @Transactional
    public List<GetClientDTO> getAllActiveClients() {
        List<Client> clients = clientRepository.getAllActiveClients();
        log.info("Get all active clients, quantity: " + clients.size());
        return clientDTOConverter.convertClientsToGetDTOs(clients);
    }

    @Override
    @Transactional
    public List<GetClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        log.info("Get all clients, quantity: " + clients.size());
        return clientDTOConverter.convertClientsToGetDTOs(clients);
    }

    @Override
    @Transactional
    public void updateClientById(UpdateClientDTO updateDTO) {
        checkIfNotExist(updateDTO.getId());
        Client client = clientDTOConverter.convertUpdateDTOToClient(updateDTO);
        clientRepository.save(client);
        log.info("Client with id: " + client.getId() + " is updated");
    }

    @Override
    @Transactional
    public void markAsDeletedClientById(UUID uuid) {
        checkIfNotExist(uuid);
        Client client = clientRepository.getReferenceById(uuid);
        client.setActive(false);
        clientRepository.save(client);
        log.info("Client with id: " + uuid + " mark as deleted");
    }

    private void checkIfNotExist(String data) {
        if (data == null) {
            throw new BadOperationException("Data is null!");
        }
    }

    private void checkIfNotExist(Client client) {
        if (client == null) {
            throw new BadOperationException("Entity with chosen parameters " +
                    "doesn't exist!");
        }
    }

    private void checkIfNotExist(UUID uuid) {
        if (!clientRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}