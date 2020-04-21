package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.DAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.Account;
import com.company.banking.Transaction;
import com.company.banking.UserNameBankAccountIDPair;
import org.postgresql.util.PSQLException;

import java.sql.Timestamp;
import java.util.ArrayList;

import static com.company.services.TransactionServices.ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE;
import static com.company.services.UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount;

/***
 * This class contains static methods to provide business logic to for processing bank accounts, including the transactions performed on them.
 *
 * @author Shawyn Kane
 */
public class BankAccountServices {

    public static final String ACCOUNT_DOES_NOT_EXIST = "The bank account with the provided accountID does not exist.";

    public static boolean accountExists(int accountID, BankAccountDAO bankAccountDAO) {
        Account[] accounts = bankAccountDAO.retrieveByID(accountID);
        return (accounts != null && accounts.length == 1);
    }

    public static ArrayList<Account> retrieveAllAccountsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws PSQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        for (UserNameBankAccountIDPair pair: userNameBankAccountIDPairDAO.retrieveByID(username)) accounts.add(bankAccountDAO.retrieveByID(pair.getAccountID())[0]);
        return accounts;
    }

    public static void printToScreenAllAccountsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws PSQLException {
        for (Account account: retrieveAllAccountsAssociatedWithUser(username, bankAccountDAO, userNameBankAccountIDPairDAO)) account.printToScreen();
    }

    public static void printToScreenAllAccountIDsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws PSQLException {
        System.out.println("\nThe accountIDs for your accounts are the following:");
        for (Account account: BankAccountServices.retrieveAllAccountsAssociatedWithUser(username, bankAccountDAO, userNameBankAccountIDPairDAO)) {
            System.out.println("\t" + account.getAccountID());
        }
        System.out.println();
    }

    public static Account retrieveAccount(int accountID, BankAccountDAO bankAccountDAO) throws PSQLException {
        Account[] accounts = bankAccountDAO.retrieveByID(accountID);
        if (accounts != null && accounts.length == 1) return accounts[0];
        else return null;
    }

    public static void printAccountToScreen(int accountID, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (checkIfUserHasAccessToAccount(accountID, username, pairDAO)) {
            Account account = retrieveAccount(accountID, bankAccountDAO);
            if (account == null) System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
            else account.printToScreen();
        } else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }

    public static void updateAccount(int accountID, double amount, String description, BankAccountDAO bankAccountDAO) throws PSQLException {
        if (!accountExists(accountID, bankAccountDAO)) {
            System.out.println();
            return;
        }

        Account account = retrieveAccount(accountID, bankAccountDAO);
        Transaction newTransaction = new Transaction(Transaction.NEW_TRANSACTIONID, account.getCurrentBalance(), amount, description, new Timestamp(System.currentTimeMillis()));
        account.getBalanceHistory().add(newTransaction);
        account.setCurrentBalance(newTransaction.getUpdatedBalance());
        bankAccountDAO.update(account);
    }


    public static void createBankAccount(String username, double amount, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO, LoginAccountDAO loginAccountDAO) throws PSQLException {
        if (amount < 0) {
            System.out.println("Please enter an amount greater than or equal to zero.");
            return;
        }


        if (LoginServices.customerExists(username, loginAccountDAO)) {
            System.out.println("Username does not exist for any customer.");
            return;
        }

        Account account = new Account(1, amount, new ArrayList<Transaction>());
        int accountID = DAO.OPERATION_FAILED;
        do {
            accountID = bankAccountDAO.save(account);
        } while (accountID == DAO.OPERATION_FAILED);

        int returnedID = DAO.OPERATION_FAILED;
        do {
            returnedID = pairDAO.save(new UserNameBankAccountIDPair(accountID, username));
        } while (returnedID == DAO.OPERATION_FAILED);
    }

    public static void deleteBankAccount(int accountID, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (accountID < 0) {
            System.out.println("AccountID has to be equal to or greater than zero.");
            return;
        }

        if (!accountExists(accountID, bankAccountDAO)) {
            System.out.println(ACCOUNT_DOES_NOT_EXIST);
            return;
        }

        pairDAO.delete(accountID);
        bankAccountDAO.delete(new Account(accountID));

    }

}
