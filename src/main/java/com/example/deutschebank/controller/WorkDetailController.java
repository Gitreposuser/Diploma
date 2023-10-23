package com.example.deutschebank.controller;

import com.example.deutschebank.dto.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.dto.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.dto.workdetail.UpdateWorkDetailDTO;
import com.example.deutschebank.service.interfaces.WorkDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/work-detail")
public class WorkDetailController {
    private final WorkDetailService workDetailService;

    @PostMapping(value = "/create")
    public void createWorkDetail(@RequestBody CreateWorkDetailDTO createDTO) {
        workDetailService.createWorkDetail(createDTO);
    }

    @GetMapping(value = "/get/by/id/{uuid}")
    public ResponseEntity<GetWorkDetailDTO> getWorkDetailById(@PathVariable UUID uuid) {
        GetWorkDetailDTO getDTO = workDetailService.getWorkDetailById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetWorkDetailDTO>> getAllWorkDetails() {
        List<GetWorkDetailDTO> getAllDTOs =
                workDetailService.getAllWorkDetails();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTOs);
    }

    @PutMapping(value = "/update/by/id")
    public void updateWorkDetailById(@RequestBody UpdateWorkDetailDTO updateDTO) {
        workDetailService.updateWorkDetailById(updateDTO);
    }
}