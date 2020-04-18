package com.company.consoleMenus;

import com.company.DAO.BankAccountDAO;
import com.company.application.ATMApplication;
import com.company.banking.Account;
import com.company.services.BankAccountServices;
import com.company.services.LoginServices;
import com.company.services.UserAccountBankAccountAssociationServices;

import java.math.BigDecimal;

public class TransactionInputScreen extends InputScreen {
    private static final String NON_NUMBER_INPUT_MESSAGE_RESPONSE = "Please input a number.";


    @Override
    public Screen run(ATMApplication app) {
        final String notAnOption = "The input given is not an option. Please enter just the number for the option.";
        // TODO add transaction and save to database
        // TODO Add Admin options like 'create account' and 'delete account'
        while(true) {
            if (app.getCredentialsEntered().isAdmin()) {
                switch (prompt(app.getScan(), "Select one of the following options by typing it's number.\n\t1) Create Bank Account\n\t2) Delete Bank Account\n\t3) Create Login Account\n\t4) Delete Login Account\n\t5) Add customer to bank account \n\t6) Logout")[0]) {
                    case "1":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the name of the username of the customer for the bank account:", "Enter the initial amount to put in the new account:");
                            BankAccountServices.createBankAccount(input[0], BankAccountServices.parseAmount(input[1]), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO(), app.getLoginAccountDAO());
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
                            LoginServices.createLoginAccount(input[0], input[1], Boolean.parseBoolean(input[3]), app.getLoginAccountDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "4":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the username:");
                            LoginServices.deleteLoginAccount(input[0], app.getLoginAccountDAO(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
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
                        System.out.println(notAnOption);
                }
            } else {
                switch (prompt(app.getScan(), "Select one of the following options by typing it's number.\n\t1) Deposit\n\t2) Withdraw\n\t3) Transfer\n\t4) Display AccountsIDs for Current User\n\t5) Display Account\n\t6) Logout")[0]) {
                    case "1":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to deposit to:", "Enter the amount you would like to deposit:");
                            BankAccountServices.doDepositOnAccount(Integer.parseInt(input[0]), BankAccountServices.parseAmount(input[1]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "2":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to withdraw from:", "Enter the amount you would like to withdraw:");
                            BankAccountServices.doWithdrawalOnAccount(Integer.parseInt(input[0]), BankAccountServices.parseAmount(input[1]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "3":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to transfer from:", "Enter the AccountID of the account you wish to transfer to:", "Enter the amount you would like to transfer:");
                            BankAccountServices.doTransferOnAccounts(Integer.parseInt(input[0]), Integer.parseInt(input[1]), BankAccountServices.parseAmount(input[2]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "4":
                        BankAccountServices.printToScreenAllAccountsAssociatedWithUser("", null, null);
                        BankAccountServices.printToScreenAllAccountsAssociatedWithUser(app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        break;
                    case "5":
                        try {
                            BankAccountServices.printAccountToScreen(Integer.parseInt(prompt(app.getScan(), "AccountID:")[0]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "6":
                        app.setCredentialsEntered(null);
                        return new LoginScreen();
                    default:
                        System.out.println(notAnOption);
                }
            }
        }
    }
}
