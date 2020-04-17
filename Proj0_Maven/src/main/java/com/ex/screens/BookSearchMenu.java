package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;

public class BookSearchMenu extends MenuScreen {
    public BookSearchMenu() {
        super("Search for Books");
        addMenuOption("Search by title");
        addMenuOption("Search by author");
        addMenuOption("Search by barcode");
        addMenuOption("Return to the previous menu");
    }
    public BookSearchMenu(Screen prevScreen) {
        this();
        setPrevScreen(prevScreen);
    }
    @Override
    public Screen doScreen(Application app) {
        displayMenu();

        switch (getMenuChoice(((LibraryApp)app).getInputSource()))
        {
            case 1: // Search by title
                System.out.println("title search");
                break;
            case 2: // Search by author
                System.out.println("author search");
                break;
            case 3: // Search by barcode
                System.out.println("barcode search");
                break;
            case 4: // Go back
                return getPrevScreen();
            default:
                System.err.println("wut");
        }

        // TODO figure out what to do after a search is completed
        return this;
    }
}
