package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;

public class BookManagementMenu extends MenuScreen {
    public BookManagementMenu() {
        super("Manage library collection");
        addMenuOption("Find a book");
        addMenuOption("Edit information about a book");
        addMenuOption("Add a book to the collection");
        addMenuOption("Remove a book from the collection");
        addMenuOption("Return to the previous menu");
    }
    public BookManagementMenu(Screen prevScreen) {
        this();
        setPrevScreen(prevScreen);
    }

    @Override
    public Screen doScreen(Application app) {
        displayMenu();

        switch (getMenuChoice(((LibraryApp)app).getInputSource()))
        {
            case 1: // Find book
                return new BookSearchMenu(this);
            case 2: // Edit book
                return new EditBookScreen(this);
            case 3: // Add book
                System.out.println("Add book");
                break;
            case 4: // Remove book
                System.out.println("Remove book");
                break;
            case 5: // Go back
                return getPrevScreen();
            default: // I don't know how we would ever hit this, but okay
                System.err.println("What?!");
        }

        // TODO what's after this?
        return this;
    }
}
