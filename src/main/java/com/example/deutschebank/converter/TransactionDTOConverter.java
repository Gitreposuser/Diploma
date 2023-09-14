package com.example.deutschebank.converter;

import com.example.deutschebank.entity.Transaction;
import com.example.deutschebank.entity.WorkDetail;
import com.example.deutschebank.model.transaction.CreateTransactionDTO;
import com.example.deutschebank.model.transaction.GetTransactionDTO;
import com.example.deutschebank.model.workdetail.CreateWorkDetailDTO;
import com.example.deutschebank.model.workdetail.GetWorkDetailDTO;
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

    public GetTransactionDTO convertTransactionToGetDTO(Transaction transaction) {
        return modelMapper.map(transaction,
                GetTransactionDTO.class);
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
