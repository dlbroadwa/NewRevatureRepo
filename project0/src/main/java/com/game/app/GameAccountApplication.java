package com.game.app;

import com.game.data.AccountSQLRepo;
import com.game.data.MessageSQLRepo;
import com.game.screens.EntryScreen;
import com.game.screens.Screen;
import com.game.service.AccountService;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;

import java.util.Scanner;

/**
 * main application class/layer
 * connections are established on this layer
 */

public class GameAccountApplication extends Application {
    Scanner in;
    AccountService accountService;
    private Screen curr;

    /**
     * run only plays screen passing into it the class and its methods
     * One scanner is used throughout the application
     * Same applies to the service
     */
    @Override
    public void run() {
        while(curr!=null){
            curr=curr.doScreen(this);
        }
    }

    public Scanner getScanner() {
        return in;
    }

    public AccountService getAccountService(){
        return accountService;
    }

    /**
     * establishes the postgresql connections and passes them into the repos
     * passes the repos into the service
     */
    @Override
    public void start() {
        in=new Scanner(System.in);
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://dyltrashs.crxekrgyc1qs.us-east-2.rds.amazonaws.com:5432/dyltrashs",
                "dyltra", "password");
        AccountSQLRepo accounts = new AccountSQLRepo(connectionUtils);
        MessageSQLRepo messages = new MessageSQLRepo(connectionUtils);
        accountService = new AccountService(accounts, messages);
        accountService.boot();
        curr = new EntryScreen();
    }

}
