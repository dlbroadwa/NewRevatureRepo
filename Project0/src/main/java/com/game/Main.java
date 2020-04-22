package com.game;

import com.game.app.Application;
import com.game.app.GameAccountApplication;

public class Main {

    public static void main(String[] args) {
        Application gameAccountApplication = new GameAccountApplication();
        gameAccountApplication.start();
        gameAccountApplication.run();
    }
}
