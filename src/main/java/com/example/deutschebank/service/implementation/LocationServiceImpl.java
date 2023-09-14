package com.example.deutschebank.service.implementation;

import com.example.deutschebank.converter.LocationDTOConverter;
import com.example.deutschebank.entity.Location;
import com.example.deutschebank.exception.BadOperationException;
import com.example.deutschebank.model.location.CreateLocationDTO;
import com.example.deutschebank.model.location.GetLocationDTO;
import com.example.deutschebank.model.location.UpdateLocationDTO;
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
        log.info("Entity successfully created.");
    }

    @Override
    public GetLocationDTO getLocation(UUID uuid) {
        checkIfNotExist(uuid);
        Location location = locationRepository.getReferenceById(uuid);
        return locationDTOConverter.convertLocationToGetDTO(location);
    }

    @Override
    public List<GetLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locationDTOConverter.convertLocationsToGetDTOs(locations);
    }

    @Override
    @Transactional
    public void updateLocation(UpdateLocationDTO updateDTO) {
        checkIfNotExist(updateDTO.id);
        Location location =
                locationDTOConverter.convertUpdateDTOToLocation(updateDTO);
        locationRepository.save(location);
        log.info("Entity with id: " + location.getId() + " is updated." );
    }

    @Override
    public void deleteLocation(UUID uuid) {
        checkIfNotExist(uuid);
        locationRepository.deleteById(uuid);
        log.info("Entity with id: " + uuid + " where successfully deleted.");
    }

    private void checkIfNotExist(UUID uuid) {
        if(!locationRepository.existsById(uuid)) {
            throw new BadOperationException("Cannot update entity! Entity " +
                    "doesn't exist!");
        }
    }
}
