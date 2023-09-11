package com.example.deutschebank.controller;

import com.example.deutschebank.model.location.CreateLocationDTO;
import com.example.deutschebank.model.location.GetLocationDTO;
import com.example.deutschebank.model.location.UpdateLocationDTO;
import com.example.deutschebank.service.interfaces.LocationService;
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
@RequestMapping(value = "/location")
public class LocationController {
    private final LocationService locationService;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateLocationDTO> createLocation(@RequestBody CreateLocationDTO createDTO) {
        locationService.createLocation(createDTO);
        return ResponseEntity.status(HttpStatus.OK).body(createDTO);
    }

    @GetMapping(value = "/get/by-id/{uuid}")
    public ResponseEntity<GetLocationDTO> getLocation(@PathVariable UUID uuid) {
        GetLocationDTO getDTO = locationService.getLocation(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetLocationDTO>> getAllLocations() {
        List<GetLocationDTO> getAllDTOs =
                locationService.getAllLocations();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by-id")
    public ResponseEntity<UpdateLocationDTO> updateLocation(@RequestBody UpdateLocationDTO updateDTO) {
        locationService.updateLocation(updateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }

    @DeleteMapping(value = "delete/by-id/{uuid}")
    public void deleteLocation(@PathVariable UUID uuid) {
        locationService.deleteLocation(uuid);
    }
}