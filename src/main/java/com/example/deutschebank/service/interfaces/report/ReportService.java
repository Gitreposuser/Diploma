package com.example.deutschebank.service.interfaces.report;

import com.example.deutschebank.dto.report.GetReportTransactionsByFullNameDTO;

public interface ReportService {
    GetReportTransactionsByFullNameDTO getReportTransactionsByFullNameDTO(String fullName);
}
