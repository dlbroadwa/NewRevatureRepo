package com.company.screens;

import com.company.app.Application;

public class ViewLow implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //view items whose onHand<optLevel
        return new Menu();
    }
}
