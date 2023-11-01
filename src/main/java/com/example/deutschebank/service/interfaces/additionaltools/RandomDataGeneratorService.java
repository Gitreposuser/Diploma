package com.example.deutschebank.service.interfaces.additionaltools;

import com.example.deutschebank.entity.*;

import java.util.List;

public interface RandomDataGeneratorService {
    <T> T chooseFromList(List<T> chooseList);

    BankBranch generateBankBranch(Integer branchNumber, Location location);

    List<BankBranch> generateMultipleBankBranches(Integer quantity,
                                                  List<Location> locations);

    BankAccount generateBankInfo();

    Client generateClient(PersonalDetail personalDetail,
                          DebitAccount debitAccount,
                          Location location,
                          Employee employee);

    List<Client> generateMultipleClients(Integer quantity,
                                         List<PersonalDetail> personalDetails,
                                         List<DebitAccount> debitAccounts,
                                         List<Location> locations,
                                         List<Employee> employees);

    CreditAccount generateCreditAccount(Client client);

    List<CreditAccount> generateMultipleCreditAccounts(Integer quantity,
                                                       List<Client> clients);

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

    Transaction generateTransaction(Client emitter, Client receiver);

    List<Transaction> generateMultipleTransactions(Integer quantity,
                                                   List<Client> emitters,
                                                   List<Client> receivers);

    WorkDetail generateWorkDetail();

    List<WorkDetail> generateMultipleWorkDetails(Integer quantity);
}