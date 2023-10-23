package com.example.deutschebank.controller;

import com.example.deutschebank.dto.report.GetReportTransactionsByFullNameDTO;
import com.example.deutschebank.service.interfaces.report.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/report")
public class ReportController {
    private final ReportService reportService;

    @GetMapping(value = "/min-amount/by/full-name/{full_name}")
    public ResponseEntity<GetReportTransactionsByFullNameDTO>
    getReportTransactionsByFullName(@PathVariable String full_name) {
        GetReportTransactionsByFullNameDTO responseDTO =
                reportService.getReportTransactionsByFullNameDTO(full_name);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
