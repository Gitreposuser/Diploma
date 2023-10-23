package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.LocationDTOConverter;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.dto.location.CreateLocationDTO;
import com.example.deutschebank.dto.location.GetLocationDTO;
import com.example.deutschebank.dto.location.UpdateLocationDTO;
import com.example.deutschebank.repository.LocationRepository;
import com.example.deutschebank.service.interfaces.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationDTOConverter locationDTOConverter;

    @Override
    @Transactional
    public void createLocation(CreateLocationDTO createDTO) {
        Location location =
                locationDTOConverter.converterCreateDTOToLocation(createDTO);
        locationRepository.save(location);
        log.info("Create location.");
    }

    @Override
    @Transactional
    public void createMultipleLocations(List<Location> locations) {
        locationRepository.saveAll(locations);
        log.info("Create multiple locations, quantity: " + locations.size());
    }

    @Override
    @Transactional
    public GetLocationDTO getLocation(UUID uuid) {
        checkIfNotExist(uuid);
        Location location = locationRepository.getReferenceById(uuid);
        log.info("Get location by id: " + uuid);
        return locationDTOConverter.convertLocationToGetDTO(location);
    }

    @Override
    @Transactional
    public List<GetLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        log.info("Get all locations, quantity: " + locations.size());
        return locationDTOConverter.convertLocationsToGetDTOs(locations);
    }

    @Override
    @Transactional
    public void updateLocation(UpdateLocationDTO updateDTO) {
        checkIfNotExist(updateDTO.getId());
        Location location =
                locationDTOConverter.convertUpdateDTOToLocation(updateDTO);
        locationRepository.save(location);
        log.info("Update location with id: " + location.getId());
    }

    @Override
    @Transactional
    public void deleteLocation(UUID uuid) {
        checkIfNotExist(uuid);
        locationRepository.deleteById(uuid);
        log.info("Delete location with id: " + uuid);
    }

    private void checkIfNotExist(UUID uuid) {
        if(uuid == null || !locationRepository.existsById(uuid)) {
            throw new BadOperationException("Cannot update entity! Entity " +
                    "doesn't exist!");
        }
    }
}
