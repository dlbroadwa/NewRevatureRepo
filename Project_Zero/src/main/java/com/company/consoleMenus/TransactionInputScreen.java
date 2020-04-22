package com.company.consoleMenus;

import com.company.application.ATMApplication;
import com.company.services.BankAccountServices;
import com.company.services.LoginServices;
import com.company.services.UserAccountBankAccountAssociationServices;
import org.postgresql.util.PSQLException;

import static com.company.services.TransactionServices.*;

/***
 * This class handles getting input from the user for the bank account transactions and admin options.
 *
 * @author Shawyn Kane
 */
public class TransactionInputScreen extends InputScreen {
    private static final String NON_NUMBER_INPUT_MESSAGE_RESPONSE = "Please input a number.";
    private static final String PLEASE_ENTER_A_NUMBER_FOR_OPTION = "The input given is not associated with an option. Please enter the number for one of the listed options.";

    @Override
    public Screen run(ATMApplication app) {

        while(true) {
            try {
                if (app.getCredentialsEntered().isAdmin()) {
                    switch (prompt(app.getScan(), "Select one of the following options by typing it's number.\n\t1) Create Bank Account\n\t2) Delete Bank Account\n\t3) Create Login Account\n\t4) Delete Login Account\n\t5) Add customer to bank account \n\t6) Logout")[0]) {
                        case "1":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the username of the customer for the bank account:", "Enter the initial amount to put in the new account:");
                                BankAccountServices.createBankAccount(input[0], parseAmount(input[1]), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO(), app.getLoginAccountDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "2":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the accountID of the bank account to delete:");
                                BankAccountServices.deleteBankAccount(Integer.parseInt(input[0]), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "3":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the username:", "Enter the Pin:", "Is the user an admin (Y/N):");
                                LoginServices.createLoginAccount(input[0], input[1], Boolean.parseBoolean(input[2]), app.getLoginAccountDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "4":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the username:");
                                LoginServices.deleteLoginAccount(input[0], app.getCredentialsEntered(), app.getLoginAccountDAO(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "5":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the username:", "Enter the accountID:");
                                UserAccountBankAccountAssociationServices.createAssociation(input[0], Integer.parseInt(input[1]), app.getUserNameBankAccountIDPairDAO(), app.getBankAccountDAO(), app.getLoginAccountDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "6":
                            app.setCredentialsEntered(null);
                            return new LoginScreen();
                        default:
                            System.out.println(PLEASE_ENTER_A_NUMBER_FOR_OPTION);
                    }
                } else {
                    switch (prompt(app.getScan(), "Select one of the following options by typing it's number:\n\t1) Deposit money into a bank account.\n\t2) Withdraw money from a bank account.\n\t3) Transfer money from one of your accounts to another account.\n\t4) Display all information for your bank accounts\n\t5) Display accountsIDs for all of your bank accounts\n\t6) Display information for an account\n\t7) Logout")[0]) {
                        case "1":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to deposit to:", "Enter the amount you would like to deposit:");
                                doDepositOnAccount(Integer.parseInt(input[0]), parseAmount(input[1]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "2":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to withdraw from:", "Enter the amount you would like to withdraw:");
                                doWithdrawalOnAccount(Integer.parseInt(input[0]), parseAmount(input[1]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "3":
                            try {
                                String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to transfer from:", "Enter the AccountID of the account you wish to transfer to:", "Enter the amount you would like to transfer:");
                                doTransferOnAccounts(Integer.parseInt(input[0]), Integer.parseInt(input[1]), parseAmount(input[2]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "4":
                            BankAccountServices.printToScreenAllAccountsAssociatedWithUser(app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            break;
                        case "5":
                            BankAccountServices.printToScreenAllAccountIDsAssociatedWithUser(app.getCredentialsEntered().getUserName(),app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            break;
                        case "6":
                            try {
                                BankAccountServices.printAccountToScreen(Integer.parseInt(prompt(app.getScan(), "AccountID:")[0]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                            } catch (NumberFormatException e) {
                                System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                            }
                            break;
                        case "7":
                            app.setCredentialsEntered(null);
                            return new LoginScreen();
                        default:
                            System.out.println(PLEASE_ENTER_A_NUMBER_FOR_OPTION);
                    }
                }
            } catch (PSQLException e) {
                System.out.println("Connection failed.");
            }
        }
    }
}
