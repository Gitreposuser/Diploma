package com.example.deutschebank.converter;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.dto.transaction.CreateTransactionDTO;
import com.example.deutschebank.dto.transaction.GetTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionDTOConverter {
    private final ModelMapper modelMapper;

    public Transaction convertCreateDTOToTransaction(CreateTransactionDTO createDTO) {
        return modelMapper.map(createDTO,
                Transaction.class);
    }

    public List<GetTransactionDTO> convertTransactionsToGetDTOs(List<Transaction> transactions) {
        List<GetTransactionDTO> getDTOs = new LinkedList<>();
        for (Transaction detail : transactions) {
            getDTOs.add(modelMapper.map(detail,
                    GetTransactionDTO.class));
        }
        return getDTOs;
    }
}
