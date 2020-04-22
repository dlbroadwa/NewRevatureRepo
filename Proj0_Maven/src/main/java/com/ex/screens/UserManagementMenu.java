package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;

public class UserManagementMenu extends MenuScreen {
    public UserManagementMenu() {
        super("Manage library users");
        addMenuOption("View user information");
        addMenuOption("Edit user information");
        addMenuOption("Register a new user");
        addMenuOption("Delete a user");
        addMenuOption("Return to the previous menu");
    }
    public UserManagementMenu(Screen prevScreen) {
        this();
        setPrevScreen(prevScreen);
    }

    @Override
    public Screen doScreen(Application app) {
        displayMenu();

        switch (getMenuChoice(((LibraryApp)app).getInputSource()))
        {
            case 1: // View user
                return new ViewUserInfoScreen(this);
            case 2: // Edit user
                return new EditUserInfoScreen(this);
            case 3: // Add user
                System.out.println("Add user");
                break;
            case 4: // Delete user
                System.out.println("Delete user");
                break;
            case 5: // Go back
                return getPrevScreen();
        }

        // TODO figure out what to do after this
        return this;
    }
}
