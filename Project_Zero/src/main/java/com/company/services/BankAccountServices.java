package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.Account;
import com.company.banking.UserNameBankAccountIDPair;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class BankAccountServices {

    public static ArrayList<Account> retrieveAllAccountsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws NullPointerException {
        if (bankAccountDAO == null) throw new NullPointerException("The BankAccountDAO parameter is null!");
        if (userNameBankAccountIDPairDAO == null) throw new NullPointerException("The UserNameBankAccountIDPairDAO parameter is null!");
        ArrayList<Account> accounts = new ArrayList<>();
        for (UserNameBankAccountIDPair pair: userNameBankAccountIDPairDAO.retrieveByID(username)) accounts.add(bankAccountDAO.retrieveByID(pair.getAccountID())[0]);
        return accounts;
    }

    public static void printToScreenAllAccountsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws NullPointerException {
        if (bankAccountDAO == null) throw new NullPointerException("The BankAccountDAO parameter is null!");
        if (userNameBankAccountIDPairDAO == null) throw new NullPointerException("The UserNameBankAccountIDPairDAO parameter is null!");
        for (Account account: BankAccountServices.retrieveAllAccountsAssociatedWithUser(username, bankAccountDAO, userNameBankAccountIDPairDAO)) account.printToScreen();
    }

    public static Account retrieveAccount(int acountID, BankAccountDAO bankAccountDAO) {
        if (bankAccountDAO == null) throw new NullPointerException("The BankAccountDAO parameter is null!");
        Account[] accounts = bankAccountDAO.retrieveByID(acountID);
        if (accounts.length == 1) return accounts[0];
        else return null;
    }

    public static void printAccountToScreen(int acountID, BankAccountDAO bankAccountDAO) {
        if (bankAccountDAO == null) throw new NullPointerException("The BankAccountDAO parameter is null!");
        retrieveAccount(acountID, bankAccountDAO).printToScreen();
    }

    public static void doWithdrawalOnAccount(int accountID, int amount, BankAccountDAO bankAccountDAO) {
        Account account = BankAccountServices.retrieveAccount(accountID, bankAccountDAO);
        // TODO finish implementing
    }

}
