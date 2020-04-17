package com.ex.screens;

import com.ex.app.Application;

public class CheckOutScreen implements Screen {
    private Screen prevScreen;

    CheckOutScreen() {}
    CheckOutScreen(Screen prevScreen) {
        this.prevScreen = prevScreen;
    }

    @Override
    public Screen doScreen(Application app) {
        // TODO
        System.out.println("check out book");
        return prevScreen;
    }
}
