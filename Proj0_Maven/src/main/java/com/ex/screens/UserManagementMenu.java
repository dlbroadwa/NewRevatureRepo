package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;

public class UserManagementMenu extends MenuScreen {
    public UserManagementMenu() {
        super("Manage library patrons");
        addMenuOption("View patron information");
        addMenuOption("Edit patron information");
        addMenuOption("Register a new patron");
        addMenuOption("Delete a patron account");
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
                return new AddUserScreen(this);
            case 4: // Delete user
                return new RemoveUserScreen(this);
            case 5: // Go back
                return getPrevScreen();
            default: // Just to shut up SonarLint. We shouldn't ever hit this case
                System.out.println("How did you do this?!");
        }

        return getPrevScreen();
    }
}
