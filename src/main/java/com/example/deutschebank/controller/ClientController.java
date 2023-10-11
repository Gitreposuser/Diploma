package com.example.deutschebank.controller;

import com.example.deutschebank.dto.client.CreateClientDTO;
import com.example.deutschebank.dto.client.GetClientDTO;
import com.example.deutschebank.dto.client.UpdateClientDTO;
import com.example.deutschebank.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping(value = "/create")
    public void createClient(@RequestBody CreateClientDTO createDTO) {
        clientService.createClient(createDTO);
    }

    @GetMapping(value = "/get/by-id/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetClientDTO> getClientById(@PathVariable UUID uuid) {
        GetClientDTO getDTO = clientService.getClientById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetClientDTO>> getAllClients() {
        List<GetClientDTO> getAllDTOs = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by-id")
    public void updateClientById(@RequestBody UpdateClientDTO updateDTO) {
        clientService.updateClientById(updateDTO);
    }

    @DeleteMapping(value = "/delete/by-id/{uuid}")
    public void deleteClientById(@PathVariable UUID uuid) {
        clientService.deleteClientById(uuid);
    }
}