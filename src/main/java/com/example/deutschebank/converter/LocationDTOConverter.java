package com.example.deutschebank.converter;

import com.example.deutschebank.entity.Location;
import com.example.deutschebank.dto.location.CreateLocationDTO;
import com.example.deutschebank.dto.location.GetLocationDTO;
import com.example.deutschebank.dto.location.UpdateLocationDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationDTOConverter {
    private final ModelMapper modelMapper;

    public Location converterCreateDTOToLocation(CreateLocationDTO createDTO) {
        return modelMapper.map(createDTO, Location.class);
    }

    public GetLocationDTO convertLocationToGetDTO(Location location) {
        return modelMapper.map(location, GetLocationDTO.class);
    }

    public List<GetLocationDTO> convertLocationsToGetDTOs(List<Location> locations) {
        List<GetLocationDTO> getDTOs = new LinkedList<>();
        for (Location location : locations) {
            getDTOs.add(modelMapper.map(location, GetLocationDTO.class));
        }
        return getDTOs;
    }

    public Location convertUpdateDTOToLocation(UpdateLocationDTO updateDTO) {
        return modelMapper.map(updateDTO, Location.class);
    }
}
