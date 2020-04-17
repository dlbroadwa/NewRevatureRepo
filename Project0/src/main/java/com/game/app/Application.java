package com.game.app;

import com.game.screens.Screen;
import com.game.service.AccountService;

import java.util.Scanner;

public abstract class Application {
    public abstract AccountService getAccountService();

    public abstract void start();

    public abstract void run();

    public abstract Scanner getScanner();

    public abstract Screen getScreen();

    public abstract void setScreen(Screen s);
}
