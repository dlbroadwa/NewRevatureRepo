package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.Transaction;
import org.postgresql.util.PSQLException;

import static com.company.services.BankAccountServices.updateAccount;
import static com.company.services.UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount;

/**
 * This class contains static methods to provide business logic to for processing bank account transactions.
 *
 * @author Shawyn Kane
 */
public class TransactionServices {
    public static final String ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE = "Sorry, you do not have access to the account with the provided accountID, or the account with the provided accountID does not exist.";

    /***
     *
     * This method just cuts off the decimal places past the 2nd decimal place and converts the string input into an int.
     * 2.344adf turns into 2.34
     * 2.345 turns into 2.34
     * 2.341 turns into 2.34
     * 2.344 turns into 2.34
     * 2.349 turns into 2.34
     *
     * @param amount
     * @return
     * @throws NumberFormatException
     */
    public static double parseAmount(String amount) throws NumberFormatException {

        String parsedAmount;
        int decimalPointIndex = amount.indexOf('.');
        if (decimalPointIndex == -1) parsedAmount = amount;
        else parsedAmount = amount.substring(0, decimalPointIndex + 3);

        return Double.parseDouble(parsedAmount);
    }

    /***
     * This method handles making a withdrawal on account.
     * This method will turn a positive amount into a negative amount for the withdrawal.
     * This method then checks if a user has access to an account and if so then updates the account by making a reference to UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount(...) and BankAccountServices.updateAccount(...).
     * UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount(...) and BankAccountServices.updateAccount(...) are imported above.
     *
     * @param accountID
     * @param amount
     * @param username
     * @param bankAccountDAO
     * @param pairDAO
     * @throws PSQLException
     */
    public static void doWithdrawalOnAccount(int accountID, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (amount > 0) amount = -amount;
        else {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (checkIfUserHasAccessToAccount(accountID, username, pairDAO)) updateAccount(accountID, amount, Transaction.WITHDRAWAL_DESCRIPTION, bankAccountDAO);
        else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }

    /***
     * This method handles making a deposit on account.
     * This method will check if the amount is greater than zero for the deposit.
     * This method then checks if a user has access to an account and if so then updates the account by making a reference to UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount(...) and BankAccountServices.updateAccount(...).
     * UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount(...) and BankAccountServices.updateAccount(...) are imported above.
     * @param accountID
     * @param amount
     * @param username
     * @param bankAccountDAO
     * @param pairDAO
     * @throws PSQLException
     */
    public static void doDepositOnAccount(int accountID, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (checkIfUserHasAccessToAccount(accountID, username, pairDAO)) updateAccount(accountID, amount, Transaction.DEPOSIT_DESCRIPTION, bankAccountDAO);
        else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }

    /***
     * This method handles making a transfer on account.
     * This method will check if the amount is greater than zero for the transfer.
     * This method then checks if a user has access to an account and if so then updates the accounts by making a reference to UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount(...) and BankAccountServices.updateAccount(...).
     * UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount(...) and BankAccountServices.updateAccount(...) are imported above.
     *
     * I did not get around to creating the logic and code to create savepoint in the database to roll back to in case the program fails in the middle of the operation.
     *
     * @param accountIDFrom
     * @param accountIDTo
     * @param amount
     * @param username
     * @param bankAccountDAO
     * @param pairDAO
     * @throws PSQLException
     */
    public static void doTransferOnAccounts(int accountIDFrom, int accountIDTo, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (checkIfUserHasAccessToAccount(accountIDFrom, username, pairDAO)) {
            updateAccount(accountIDFrom, -amount, Transaction.TRANSFER_DESCRIPTION, bankAccountDAO);
            updateAccount(accountIDTo, amount, Transaction.TRANSFER_DESCRIPTION, bankAccountDAO);
        } else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }
}
