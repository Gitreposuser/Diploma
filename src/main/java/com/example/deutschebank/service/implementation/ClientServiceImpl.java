package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.ClientDTOConverter;
import com.example.deutschebank.entity.Client;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.client.CreateClientDTO;
import com.example.deutschebank.model.client.GetClientDTO;
import com.example.deutschebank.model.client.UpdateClientDTO;
import com.example.deutschebank.repository.ClientRepository;
import com.example.deutschebank.service.interfaces.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        log.info("Entity successfully created.");
    }

    @Override
    public GetClientDTO getClientById(UUID uuid) {
        checkIfNotExist(uuid);
        Client client = clientRepository.getReferenceById(uuid);
        return clientDTOConverter.convertClientToGetDTO(client);
    }

    @Override
    public List<GetClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientDTOConverter.convertClientsToGetDTOs(clients);
    }

    @Override
    @Transactional
    public void updateClientById(UpdateClientDTO updateDTO) {
        checkIfNotExist(updateDTO.getId());
        Client client = clientDTOConverter.convertUpdateDTOToClient(updateDTO);
        clientRepository.save(client);
        log.info("Entity with id: " + client.getId() + " is updated.");
    }

    @Override
    public void deleteClientById(UUID uuid) {
        checkIfNotExist(uuid);
        clientRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkIfNotExist(UUID uuid) {
        if(!clientRepository.existsById(uuid)) {
            throw new BadOperationException("Entity with id: " + uuid +
                    "doesn't exist!");
        }
    }
}