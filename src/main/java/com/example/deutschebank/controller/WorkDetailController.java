package com.example.deutschebank.controller;

import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.service.interfaces.WorkDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<WorkDetail> createWorkDetail(@RequestBody WorkDetail workDetail) {
        log.info("start creating work detail");
        workDetailService.createWorkDetail(workDetail);
        return ResponseEntity.status(HttpStatus.OK).body(workDetail);
    }

    @PostMapping(value = "/update")
    public void updateWorkDetail(@RequestBody UUID uuid,
                                 WorkDetail workDetail) {
        workDetailService.updateWorkDetail(uuid, workDetail);
    }

    @PostMapping(value = "/get/by-id/{uuid}")
    public ResponseEntity<WorkDetail> getWorkDetail(@PathVariable("uuid") UUID uuid) {
        WorkDetail workDetail = workDetailService.getWorkDetail(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(workDetail);
    }

    @GetMapping(value = "/delete/by-id/{uuid}")
    public void deleteWorkDetail(@PathVariable("uuid") UUID uuid) {
        workDetailService.deleteWorkDetail(uuid);
    }
}