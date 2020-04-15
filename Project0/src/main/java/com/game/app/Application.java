package com.game.app;

import com.game.service.AccountService;

import java.util.Scanner;

public abstract class Application {
    public abstract AccountService getAccountService();

    public abstract void run();

    public abstract Scanner getScanner();
}
