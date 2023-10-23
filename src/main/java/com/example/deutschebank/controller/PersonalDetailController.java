package com.example.deutschebank.controller;

import com.example.deutschebank.dto.personaldetail.CreatePersonalDetailDTO;
import com.example.deutschebank.dto.personaldetail.GetPersonalDetailDTO;
import com.example.deutschebank.dto.personaldetail.UpdatePersonalDetailDTO;
import com.example.deutschebank.service.interfaces.PersonalDetailService;
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
@RequestMapping("/personal-detail")
public class PersonalDetailController {
    private final PersonalDetailService personalDetailService;

    @PostMapping(value = "/create")
    public void createPersonalDetail(@RequestBody CreatePersonalDetailDTO createDTO) {
        personalDetailService.createPersonalDetail(createDTO);
    }

    @GetMapping(value = "/get/by/id/{uuid}")
    public ResponseEntity<GetPersonalDetailDTO> getPersonalDetail(@PathVariable UUID uuid) {
        GetPersonalDetailDTO getDTO =
                personalDetailService.getPersonalDetail(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetPersonalDetailDTO>> getAllPersonalDetails() {
        List<GetPersonalDetailDTO> getAllDTOs =
                personalDetailService.getAllPersonalDetails();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by/id")
    public void updatePersonalDetail(@RequestBody UpdatePersonalDetailDTO updateDTO) {
        personalDetailService.updatePersonalDetail(updateDTO);
    }
}
