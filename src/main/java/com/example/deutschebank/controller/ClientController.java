package com.example.deutschebank.controller;

import com.example.deutschebank.entity.Client;
import com.example.deutschebank.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void createClient(@RequestBody Client client) {
        clientService.createClient(client);
    }

    @GetMapping(value = "/get/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> getClientById(@PathVariable UUID id) {
        return clientService.getClientById(id);
    }
}
