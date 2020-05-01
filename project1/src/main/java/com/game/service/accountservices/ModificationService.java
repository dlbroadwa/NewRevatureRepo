package com.game.service.accountservices;

public interface ModificationService {
    void deposit(int amount, String user);
    boolean withdraw(int amount, String user);
    void changePassword(String password, String user);
    void changeBankAccount(String bankAccount, String user);
}
