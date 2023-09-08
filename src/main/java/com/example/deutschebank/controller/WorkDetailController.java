package com.example.deutschebank.controller;

import com.example.deutschebank.model.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
import com.example.deutschebank.model.workdetail.UpdateWorkDetailDTO;
import com.example.deutschebank.service.interfaces.WorkDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for handling work details.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/work-detail")
public class WorkDetailController {
    private final WorkDetailService workDetailService;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateWorkDetailDTO>
            createWorkDetail(@RequestBody CreateWorkDetailDTO createUpdateDTO) {
        workDetailService.createWorkDetail(createUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(createUpdateDTO);
    }

    @GetMapping(value = "/get/by-id/{uuid}")
    public ResponseEntity<GetWorkDetailDTO> getWorkDetail(@PathVariable UUID uuid) {
        GetWorkDetailDTO getDTO = workDetailService.getWorkDetail(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(getDTO);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<GetWorkDetailDTO>> getAllWorkDetails() {
        List<GetWorkDetailDTO> getAllDTO =
                workDetailService.getAllWorkDetails();
        return ResponseEntity.status(HttpStatus.OK).body(getAllDTO);
    }

    @PutMapping(value = "/update/by-id")
    public void updateWorkDetail(@RequestBody UpdateWorkDetailDTO updateDTO) {
        workDetailService.updateWorkDetail(updateDTO);
    }

    @DeleteMapping(value = "/delete/by-id/{uuid}")
    public void deleteWorkDetail(@PathVariable UUID uuid) {
        workDetailService.deleteWorkDetail(uuid);
    }
}