package com.company.DataAccess;

import java.time.LocalDate;
import java.util.List;

public interface DAOI<T,ID> {
    T findAccountById(ID id);
    T findByUserNamePassword(String userName, String passWord);
    boolean updateOneAccount(ID id, double amount, String columnName);
    boolean updateAccounts(ID id, double checkingAmount, double savingAmount);
    boolean addTransaction(ID id, LocalDate date, String transaction);
    List<String> findAllTransactionsById(ID id);
    void deleteAllTransactionsById(ID id);
}
