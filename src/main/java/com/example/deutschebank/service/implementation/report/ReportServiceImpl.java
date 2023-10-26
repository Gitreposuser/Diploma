package com.example.deutschebank.service.implementation.report;

import com.example.deutschebank.dto.report.GetReportTransactionsByFullNameDTO;
import com.example.deutschebank.service.interfaces.ClientService;
import com.example.deutschebank.service.interfaces.TransactionService;
import com.example.deutschebank.service.interfaces.report.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ClientService clientService;
    private final TransactionService transactionService;

    @Override
    public GetReportTransactionsByFullNameDTO
    getReportTransactionsByFullNameDTO(String fullName) {
        String clientIban = clientService.getClientIbanByFullName(fullName).getIban();
        BigDecimal minAmount =
                transactionService.getMinAmountByEmitterIban(clientIban);
        GetReportTransactionsByFullNameDTO getReportDTO = new GetReportTransactionsByFullNameDTO();
        getReportDTO.setMinAmount(minAmount);
        return getReportDTO;
    }
}
