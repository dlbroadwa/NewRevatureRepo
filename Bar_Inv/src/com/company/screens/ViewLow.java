package com.company.screens;

import com.company.app.Application;

public class ViewLow implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //view items whose onHand<optLevel
        //present option to compile list of such items
        return new Menu();
    }
}
