package com.company.screens.admin;

import com.company.app.Application;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;

public class ViewLow implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //view items whose onHand<optLevel
        //present option to compile list of such items
        return new Menu();
    }
}
