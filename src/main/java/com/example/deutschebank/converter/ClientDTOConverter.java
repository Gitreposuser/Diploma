package com.example.deutschebank.converter;

import com.example.deutschebank.entity.Client;
import com.example.deutschebank.model.client.CreateClientDTO;
import com.example.deutschebank.model.client.GetClientDTO;
import com.example.deutschebank.model.client.UpdateClientDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientDTOConverter {
    private final ModelMapper modelMapper;

    public Client convertCreateDTOToClient(CreateClientDTO createDTO) {
        return modelMapper.map(createDTO,
                Client.class);
    }

    public GetClientDTO convertClientToGetDTO(Client workDetail) {
        return modelMapper.map(workDetail,
                GetClientDTO.class);
    }

    public List<GetClientDTO> convertClientsToGetDTOs(List<Client> clients) {
        List<GetClientDTO> getDTOs = new LinkedList<>();
        for (Client detail : clients) {
            getDTOs.add(modelMapper.map(detail,
                    GetClientDTO.class));
        }
        return getDTOs;
    }

    public Client convertUpdateDTOToClient(UpdateClientDTO updateDTO) {
        return modelMapper.map(updateDTO,
                Client.class);
    }
}
