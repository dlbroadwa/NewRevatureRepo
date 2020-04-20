package com.company.DataAccess;

import java.time.LocalDate;
import java.util.List;

public interface DAO<T,ID> {
    T findAccountById(ID id);
    T findByUserNamePassword(String userName, String passWord);
    void update(T newObj, ID id);
    void delete(T obj);
    boolean updateOneAccount(ID id, double amount, String columnName);
    boolean updateAccounts(ID id, double checkingAmount, double savingAmount);
    void addTransaction(ID id, LocalDate date, String transaction);
    List<String> findAllTransactionsById(ID id);
}
