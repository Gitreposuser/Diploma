package com.example.deutschebank.service.interfaces;


import com.example.deutschebank.dto.location.CreateLocationDTO;
import com.example.deutschebank.dto.location.GetLocationDTO;
import com.example.deutschebank.dto.location.UpdateLocationDTO;
import com.example.deutschebank.entity.Location;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    void createLocation(CreateLocationDTO createDTO);

    void createMultipleLocations(List<Location> locations);

    GetLocationDTO getLocation(UUID uuid);

    List<GetLocationDTO> getAllLocations();

    void updateLocation(UpdateLocationDTO updateDTO);

    void deleteLocation(UUID uuid);
}
