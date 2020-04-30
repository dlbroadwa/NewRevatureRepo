package com.game.service.accountservices;

public interface ModificationService {
    void deposit(int amount);
    void withdraw(int amount);
    void changePassword(String password);
    void changeBankAccount(String bankAccount);
    void adminAccess(String username);
    void standardAccess();
}
