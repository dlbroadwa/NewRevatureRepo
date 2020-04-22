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
 * This class contains static methods to provide business logic to for processing bank accounts.
 *
 * @author Shawyn Kane
 */
public class BankAccountServices {

    public static final String ACCOUNT_DOES_NOT_EXIST = "The bank account with the provided accountID does not exist.";

    /***
     * This method just checks to make
     * @param accountID
     * @param bankAccountDAO
     * @return
     */
    public static boolean accountExists(int accountID, BankAccountDAO bankAccountDAO) {
        Account[] accounts = bankAccountDAO.retrieveByID(accountID);
        return (accounts != null && accounts.length == 1);
    }

    /***
     * This method retrieves all accounts associated with a username using the BankAccountDAO class and UserNameBankAccountIDPairDAO class.
     * @param username
     * @param bankAccountDAO
     * @param userNameBankAccountIDPairDAO
     * @return
     * @throws PSQLException
     */
    public static ArrayList<Account> retrieveAllAccountsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws PSQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        for (UserNameBankAccountIDPair pair: userNameBankAccountIDPairDAO.retrieveByID(username)) accounts.add(bankAccountDAO.retrieveByID(pair.getAccountID())[0]);
        return accounts;
    }

    /***
     * This method prints all the accounts associated with a username to the console (System.out).
     * @param username
     * @param bankAccountDAO
     * @param userNameBankAccountIDPairDAO
     * @throws PSQLException
     */
    public static void printToScreenAllAccountsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws PSQLException {
        for (Account account: retrieveAllAccountsAssociatedWithUser(username, bankAccountDAO, userNameBankAccountIDPairDAO)) account.printToScreen();
    }

    /***
     * This method prints all the accountIDs of the accounts associated with a username to the console (System.out).
     * @param username
     * @param bankAccountDAO
     * @param userNameBankAccountIDPairDAO
     * @throws PSQLException
     */
    public static void printToScreenAllAccountIDsAssociatedWithUser(String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO userNameBankAccountIDPairDAO) throws PSQLException {
        System.out.println("\nThe accountIDs for your accounts are the following:");
        for (Account account: BankAccountServices.retrieveAllAccountsAssociatedWithUser(username, bankAccountDAO, userNameBankAccountIDPairDAO)) {
            System.out.println("\t" + account.getAccountID());
        }
        System.out.println();
    }

    /***
     * This method retrieves an account using the BankAccountDAO and returns null if it does not exist.
     * @param accountID
     * @param bankAccountDAO
     * @return
     * @throws PSQLException
     */
    public static Account retrieveAccount(int accountID, BankAccountDAO bankAccountDAO) throws PSQLException {
        Account[] accounts = bankAccountDAO.retrieveByID(accountID);
        if (accounts != null && accounts.length == 1) return accounts[0];
        else return null;
    }

    /***
     *
     * This method checks if a user has access to an account and if the account exists prints the account information to the console (System.out).
     * If the user does not have access or the account does not exist the method will print to the console (System.out) that the customer does not have access or the account does not exist.
     *
     * @param accountID
     * @param username
     * @param bankAccountDAO
     * @param pairDAO
     * @throws PSQLException
     */
    public static void printAccountToScreen(int accountID, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (checkIfUserHasAccessToAccount(accountID, username, pairDAO)) {
            Account account = retrieveAccount(accountID, bankAccountDAO);
            if (account == null) System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
            else account.printToScreen();
        } else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }

    /***
     * Checks if account exists and if so updates an account with a new bank account transaction using the BankAccountDAO class.
     * @param accountID
     * @param amount
     * @param description
     * @param bankAccountDAO
     * @throws PSQLException
     */
    public static void updateAccount(int accountID, double amount, String description, BankAccountDAO bankAccountDAO) throws PSQLException {
        if (!accountExists(accountID, bankAccountDAO)) {
            System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
            return;
        }

        Account account = retrieveAccount(accountID, bankAccountDAO);
        Transaction newTransaction = new Transaction(Transaction.NEW_TRANSACTIONID, account.getCurrentBalance(), amount, description, new Timestamp(System.currentTimeMillis()));
        account.getBalanceHistory().add(newTransaction);
        account.setCurrentBalance(newTransaction.getUpdatedBalance());
        bankAccountDAO.update(account);
    }

    /***
     *
     * This method handles the logic of creating a new bank account.
     * This method first checks the amount to make sure it is greater than or equal to zero.
     * The method then checks that customer that is to be associated with the new bank account exists.
     * Then the method attempts to create the bank account and make an association between the customer and bank account.
     *
     * I did not get around to creating the logic and code to create savepoint in the database to roll back to in case the program fails in the middle of the operation.
     * In this operation I just had it continuously loop to keep retrying until the operation happens because the main cause of failure that the program recovers to this point would be if the network is disconnected.
     *
     * @param username
     * @param amount
     * @param bankAccountDAO
     * @param pairDAO
     * @param loginAccountDAO
     * @throws PSQLException
     */
    public static void createBankAccount(String username, double amount, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO, LoginAccountDAO loginAccountDAO) throws PSQLException {
        if (amount < 0) {
            System.out.println("Please enter an amount greater than or equal to zero.");
            return;
        }


        if (!LoginServices.customerExists(username, loginAccountDAO)) {
            System.out.println("Username does not exist for any customer.");
            return;
        }

        Account account = new Account(1, amount, new ArrayList<Transaction>());
        Integer accountID = DAO.OPERATION_FAILED;
        do {
            accountID = bankAccountDAO.save(account);
        } while (accountID == DAO.OPERATION_FAILED);

        Integer returnedID = DAO.OPERATION_FAILED;
        do {
            returnedID = pairDAO.save(new UserNameBankAccountIDPair(accountID, username));
        } while (returnedID == DAO.OPERATION_FAILED);
    }

    /***
     * This method handles the business logic of deleting a bank account.
     * This method first checks to make sure if the accountID is equal to or greater than 1.
     * Then the method checks to make sure the account exists.
     * Then deletes the username associations with the account.
     * Then deletes the account.
     *
     * I did not get around to creating the logic and code to create savepoint in the database to roll back to in case the program fails in the middle of the operation.
     * In this operation I just had it continuously loop to keep retrying until the operation happens because the main cause of failure that the program recovers to this point would be if the network is disconnected.
     * I do want to note that if this operation was interrupted and all the usernames and accountID pairs are deleted and not the account, that the accounts would not be able to be access by a customer.
     * The inaccessible accounts would have to be purged/deleted manually with a sql command in the database.
     *
     *  @param accountID
     * @param bankAccountDAO
     * @param pairDAO
     * @throws PSQLException
     */
    public static void deleteBankAccount(int accountID, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (accountID < 1) {
            System.out.println("AccountID has to be equal to or greater than 1.");
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
