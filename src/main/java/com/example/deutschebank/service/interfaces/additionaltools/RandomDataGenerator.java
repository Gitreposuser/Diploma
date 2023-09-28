package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.entity.*;

import java.util.List;
import java.util.UUID;

public interface RandomDataGenerator {

    UUID chooseFromList(List<UUID> uuidList);

    BankBranch generateBankBranch(int branchNumber,
                                  UUID locationId);

    BankInfo generateBankInfo();

    Client generateClient(UUID managerId,
                          UUID debitAccountId,
                          UUID personalDetailId,
                          UUID locationId);

    CreditAccount generateCreditAccount(UUID clientId);

    DebitAccount generateDebitAccount();

    Employee generateEmployee(UUID personalDetailId,
                              UUID workDetailId,
                              UUID locationId,
                              UUID branchId);

    Location generateLocation();

    PersonalDetail generatePersonalDetail();

    Transaction generateTransaction();

    WorkDetail generateWorkDetail();
}