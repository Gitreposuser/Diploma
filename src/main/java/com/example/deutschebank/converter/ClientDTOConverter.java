package com.example.deutschebank.converter;

import com.example.deutschebank.dto.client.*;
import com.example.deutschebank.entity.Client;
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

    public GetClientDTO convertClientToGetDTO(Client client) {
        return modelMapper.map(client,
                GetClientDTO.class);
    }

    public GetClientInfoDTO convertClientToGetClientInfoDTO(Client client) {
        GetClientInfoDTO getInfoDTO = modelMapper
                .typeMap(Client.class, GetClientInfoDTO.class)
                //.addMapping(src -> src.getId(), GetClientInfoDTO::setId)
                .addMapping(src -> src.getPersonalDetail().getFirstName(),
                        GetClientInfoDTO::setFirstName)
                .addMapping(src -> src.getPersonalDetail().getLastName(),
                        GetClientInfoDTO::setLastName)
                .addMapping(src -> src.getPersonalDetail().getPhone(),
                        GetClientInfoDTO::setPhone)
                .addMapping(src -> src.getPersonalDetail().getEmail(),
                        GetClientInfoDTO::setEmail)
                .addMapping(src -> src.getPersonalDetail().getAge(),
                        GetClientInfoDTO::setAge)
                .addMapping(src -> src.getPersonalDetail().getBirthDate(),
                        GetClientInfoDTO::setBirthDate)
                .addMapping(src -> src.getLocation().getCountry(),
                        GetClientInfoDTO::setCountry)
                .addMapping(src -> src.getLocation().getCity(),
                        GetClientInfoDTO::setCity)
                .addMapping(src -> src.getDebitAccount().getIban(),
                        GetClientInfoDTO::setIban)
                .addMapping(src -> src.getDebitAccount().getDebitStatus(),
                        GetClientInfoDTO::setDebitStatus)
                .addMapping(src -> src.getDebitAccount().getBalance(),
                        GetClientInfoDTO::setBalance)
                .addMapping(src -> src.getDebitAccount().getDepositInterest(),
                        GetClientInfoDTO::setDepositInterest)
                .addMapping(src -> src.getEmployee().getId(),
                        GetClientInfoDTO::setManagerId)
                .addMapping(src -> src.getEmployee().getPersonalDetail().getFirstName(),
                        GetClientInfoDTO::setManagerFirstName)
                .addMapping(src -> src.getEmployee().getPersonalDetail().getLastName(),
                        GetClientInfoDTO::setManagerLastName)
                .map(client);
        return getInfoDTO;
    }

    public GetClientIbanDTO convertClientToGetClientIbanDTO(Client client) {
        GetClientIbanDTO getIbanDTO = modelMapper
                .typeMap(Client.class, GetClientIbanDTO.class)
                .addMapping(src -> src.getDebitAccount().getIban(),
                        GetClientIbanDTO::setIban)
                .map(client);
        return getIbanDTO;
    }

    public List<GetClientDTO> convertClientsToGetDTOs(List<Client> clients) {
        List<GetClientDTO> getDTOs = new LinkedList<>();
        for (Client detail : clients) {
            getDTOs.add(modelMapper.map(detail, GetClientDTO.class));
        }
        return getDTOs;
    }

    public Client convertUpdateDTOToClient(UpdateClientDTO updateDTO) {
        return modelMapper.map(updateDTO, Client.class);
    }
}
