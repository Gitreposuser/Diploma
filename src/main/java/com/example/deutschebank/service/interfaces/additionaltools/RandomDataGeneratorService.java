package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.entity.*;

import java.util.List;

public interface RandomDataGeneratorService {
    <T> T chooseFromList(List<T> chooseList);

    BankBranch generateBankBranch(Integer branchNumber, Location location);

    List<BankBranch> generateMultipleBankBranches(Integer quantity,
                                                  List<Location> locations);

    BankInfo generateBankInfo();
/*

    CreateClientDTO generateClient(PersonalDetail personalDetailId,
                                   WorkDetail workDetailId,
                                   Location locationId,
                                   BankBranch branchId);

    CreateCreditAccountDTO generateCreditAccount(Client client);
     */

    DebitAccount generateDebitAccount();

    List<DebitAccount> generateMultipleDebitAccounts(Integer quantity);

    Employee generateEmployee(PersonalDetail personalDetail,
                              WorkDetail workDetail,
                              Location location,
                              BankBranch bankBranch);

    List<Employee> generateMultipleEmployees(Integer quantity,
                                             List<PersonalDetail> personalDetails,
                                             List<WorkDetail> workDetails,
                                             List<Location> locations,
                                             List<BankBranch> bankBranches);

    Location generateLocation();

    List<Location> generateMultipleLocations(Integer quantity);

    PersonalDetail generatePersonalDetail();

    List<PersonalDetail> generateMultiplePersonalDetails(Integer quantity);

    WorkDetail generateWorkDetail();

    List<WorkDetail> generateMultipleWorkDetails(Integer quantity);
}