package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;

public class MainMenu extends MenuScreen {
    public MainMenu() {
        super("Welcome to Library Administration");
        addMenuOption("Check in book");
        addMenuOption("Check out book to patron");
        addMenuOption("Find a book");
        addMenuOption("Manage library patrons");
        addMenuOption("Manage book collection");
        addMenuOption("Quit");
    }
    @Override
    public Screen doScreen(Application app) {
        displayMenu();

        switch (getMenuChoice(((LibraryApp)app).getInputSource()))
        {
            case 1: // Check in
                return new CheckInScreen(this);
            case 2: // Check out
                return new CheckOutScreen(this);
            case 3: // Search for book
                return new BookSearchMenu(this);
            case 4: // Manage user database
                return new UserManagementMenu(this);
            case 5: // Manage book database
                return new BookManagementMenu(this);
            case 6: // Quit
                break;
            default: // We shouldn't be here
                System.err.println("what");
        }

        return null;
    }
}
