package com.ex.account_actions;

import com.ex.Runner;
import com.ex.Screen;
import com.ex.data.AccountSQLDatabase;
import com.ex.data.GenericDAO;
import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

/**
 * AccountUpdate Created By: Paityn Maynard on April 30,2020
 * Paityn Maynard: Added account, oldEmail, newEmail, newName,newPassword, isManager, isEmployee, choice, Constructors, doScreen Method -April 30
 */
public class AccountUpdate {
 //Instant Variables
    Account account = new Account();
    String oldEmail, newEmail, newName, newPassword;
    Boolean isManager, isEmployee;
    int choice;

//Constructors
    public AccountUpdate(){

    }

//Methods
    public Screen doScreen(Runner anInterface) {
        DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "postgres","revature","project1");
        GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);

        account= accounts.findByID(oldEmail);
        //GET CHOICE OF UPDATE 1: NAME; 2:EMAIL; 3:PASSWORD; 4: EMPLOYEE; 5:MANAGER
        switch (choice){
            case 1://SET VARIABLES WITH HTML??
                account.setName(newName);
                break;
            case 2:
                account.setEmail(newEmail);
                break;
            case 3:
                account.setPassword(newPassword);
                break;
            case 4:
                account.setEmployee(isEmployee);
                break;
            case 5:
                account.setManager(isManager);
                break;
            default:
                break;
        }

        accounts.update(oldEmail,account);

        return null;
    }
}
