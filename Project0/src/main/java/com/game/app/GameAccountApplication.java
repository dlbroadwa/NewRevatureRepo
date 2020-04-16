package com.game.app;

import com.game.data.AccountSQLRepo;
import com.game.screens.EntryScreen;
import com.game.screens.Screen;
import com.game.service.AccountService;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;

import java.util.Scanner;

public class GameAccountApplication extends Application {
    Scanner in;
    AccountService accountService;

    @Override
    public void run() {
        in=new Scanner(System.in);
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://dyltrashs.crxekrgyc1qs.us-east-2.rds.amazonaws.com:5432/dyltrashs",
                "dyltra", "password");
        AccountSQLRepo accounts = new AccountSQLRepo(connectionUtils);
        accountService = new AccountService(accounts);
        accountService.boot();
        Screen curr = new EntryScreen();

        while(curr!=null){
            curr=curr.doScreen(this);
        }
    }

    @Override
    public Scanner getScanner() {
        return in;
    }

    @Override
    public AccountService getAccountService(){
        return accountService;
    }
}
