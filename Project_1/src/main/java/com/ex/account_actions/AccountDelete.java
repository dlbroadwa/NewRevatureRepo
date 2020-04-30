package com.ex.account_actions;

import com.ex.Runner;
import com.ex.Screen;
import com.ex.data.AccountSQLDatabase;
import com.ex.data.GenericDAO;
import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

/**
 * AccountDelete Created By: Paityn Maynard on April 30,2020
 * Paityn Maynard: Added account, email, Constructors, doScreen Method -April 30
 */
public class AccountDelete implements Screen {
//Instant Variables
    private Account account = new Account();
    private String email;

//Constructors
    public AccountDelete(){

    }

//Methods
    public Screen doScreen(Runner anInterface) {
        DatabaseConnection connectionUtils = new PostgreSQLConnection("jdbc:postgresql://project1database.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres",
                "postgres","revature","project1");
        GenericDAO<Account,String> accounts = new AccountSQLDatabase(connectionUtils);

        //SET email THROUGH HTML?

        account.setEmail(email);

        accounts.remove(account.getEmail());
        return null;
    }
}
