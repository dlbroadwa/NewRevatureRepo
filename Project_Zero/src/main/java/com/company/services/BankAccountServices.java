package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.Account;
import com.company.banking.Transaction;
import com.company.banking.UserNameBankAccountIDPair;
import com.company.loginAccounts.LoginAccount;

import java.sql.Timestamp;
import java.util.ArrayList;

public class BankAccountServices {
    private static final String ACCOUNT_DOES_NOT_EXIST_MESSAGE = "Sorry, you do not have access to the account with the provided accountID, or the account with the provided accountID does not exist.";

    public static double parseAmount(String amount) throws NumberFormatException {

        String parsedAmount;
        int decimalPointIndex = amount.indexOf('.');
        if (decimalPointIndex == -1) parsedAmount = amount;
        else parsedAmount = amount.substring(0, decimalPointIndex + 3);

        return Double.parseDouble(parsedAmount);
    }

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

    public static void printAccountToScreen(int acountID, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) {

        if (bankAccountDAO == null) throw new NullPointerException("The BankAccountDAO parameter is null!");
        if (pairDAO.relationshipBetweenUserAndAccountExists(new UserNameBankAccountIDPair(acountID, username))) {
            Account account = retrieveAccount(acountID, bankAccountDAO);
            if (account == null) System.out.println(ACCOUNT_DOES_NOT_EXIST_MESSAGE);
            else account.printToScreen();
        } else System.out.println(ACCOUNT_DOES_NOT_EXIST_MESSAGE);
    }

    public static void updateAccount(int accountID, double amount, String description, BankAccountDAO bankAccountDAO) {
        Account account = BankAccountServices.retrieveAccount(accountID, bankAccountDAO);
        Transaction newTransaction = new Transaction(Transaction.NEW_TRANSACTIONID, account.getCurrentBalance(), amount, description, new Timestamp(System.currentTimeMillis()));
        account.getBalanceHistory().add(newTransaction);
        account.setCurrentBalance(newTransaction.getUpdatedBalance());
        bankAccountDAO.update(account);
    }

    public static void doWithdrawalOnAccount(int accountID, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) {
        if (amount > 0) amount = -amount;
        else {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }
        if (pairDAO.relationshipBetweenUserAndAccountExists(new UserNameBankAccountIDPair(accountID, username))) updateAccount(accountID, amount, Transaction.WITHDRAWAL_DESCRIPTION, bankAccountDAO);
        else System.out.println(ACCOUNT_DOES_NOT_EXIST_MESSAGE);
    }

    public static void doDepositOnAccount(int accountID, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (pairDAO.relationshipBetweenUserAndAccountExists(new UserNameBankAccountIDPair(accountID, username))) updateAccount(accountID, amount, Transaction.DEPOSIT_DESCRIPTION, bankAccountDAO);
        else System.out.println(ACCOUNT_DOES_NOT_EXIST_MESSAGE);
    }

    public static void doTransferOnAccounts(int accountIDFrom, int accountIDTo, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (pairDAO.relationshipBetweenUserAndAccountExists(new UserNameBankAccountIDPair(accountIDFrom, username))) {
            updateAccount(accountIDFrom, -amount, Transaction.TRANSFER_DESCRIPTION, bankAccountDAO);
            updateAccount(accountIDTo, amount, Transaction.TRANSFER_DESCRIPTION, bankAccountDAO);
        } else System.out.println(ACCOUNT_DOES_NOT_EXIST_MESSAGE);
    }

    public static void createBankAccount(String username, double amount, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO, LoginAccountDAO loginAccountDAO) {
        if (amount < 0) {
            System.out.println("Please enter an amount greater than or equal to zero.");
            return;
        }

        LoginAccount[] accounts = loginAccountDAO.retrieveByID(username);
        if ((accounts.length == 0 || accounts == null) || (accounts.length == 1 && accounts[0].isAdmin())) {
            System.out.println("Username does not exist for any customer.");
            return;
        }

        Account account = new Account(0, amount, new ArrayList<Transaction>());
        pairDAO.save(new UserNameBankAccountIDPair(bankAccountDAO.save(account), username));
    }

    public static void deleteBankAccount(int accountID, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) {
        if (accountID < 0) {
            System.out.println("AccountID has to be equal to or greater than zero.");
            return;
        }

        bankAccountDAO.delete(new Account(accountID));
        pairDAO.delete(accountID);
    }

}
