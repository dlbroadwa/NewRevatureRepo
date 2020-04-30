package bank.services;

import java.time.LocalDate;

public class AccountService {


    public void deposit(String userName, int accountID, double amount) {

    }

    public void withdraw(String userName, int accountID, double amount) {
        double currentAmount = 0 - amount;
    }

    public void transfer(String userName, int userAccountID, double amount, int transferredAccountID) {
    }
}
