package com.example.deutschebank.controller;

import com.example.deutschebank.dto.client.*;
import com.example.deutschebank.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping(value = "/create")
    public void createClient(@RequestBody CreateClientDTO createDTO) {
        clientService.createClient(createDTO);
    }

    @GetMapping(value = "/get/by/id/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetClientDTO> getClientById(@PathVariable UUID uuid) {
        GetClientDTO getDTO = clientService.getClientById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/by/full-name/{full_name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetClientDTO> getClientByFullName(@PathVariable String full_name) {
        GetClientDTO getDTO = clientService.getClientByFullName(full_name);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all/active")
    public ResponseEntity<List<GetClientDTO>> getAllActiveClients() {
        List<GetClientDTO> getAllDTOs = clientService.getAllActiveClients();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetClientDTO>> getAllClients() {
        List<GetClientDTO> getAllDTOs = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @GetMapping(value = "/get/iban/by/full-name/{full_name}")
    public ResponseEntity<GetClientIbanDTO> getClientIbanByFullName
            (@PathVariable String full_name) {
        GetClientIbanDTO getIbanDTO = clientService.getClientIbanByFullName(full_name);
        return ResponseEntity.status(HttpStatus.OK).body(getIbanDTO);
    }

    @GetMapping(value = "/get/info/by/full-name/{full_name}")
    public ResponseEntity<GetClientInfoDTO> getClientInfoByFullName
            (@PathVariable String full_name) {
        GetClientInfoDTO getDTO =
                clientService.getClientInfoByFullName(full_name);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @PutMapping(value = "/update/by/id")
    public void updateClientById(@RequestBody UpdateClientDTO updateDTO) {
        clientService.updateClientById(updateDTO);
    }

    @DeleteMapping(value = "/delete/by/id/{uuid}")
    public void markAsDeletedClientById(@PathVariable UUID uuid) {
        clientService.markAsDeletedClientById(uuid);
    }
}