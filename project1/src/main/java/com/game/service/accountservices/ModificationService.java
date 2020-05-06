package com.game.service.accountservices;

public interface ModificationService {
    boolean deposit(int amount, String user);
    boolean withdraw(int amount, String user);
    void changePassword(String password, String user);
    boolean changeBankAccount(String bankAccount, String user);
}
