package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.services.BookSearchService;

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
                return new BookSearchScreen(BookSearchService.SearchType.TITLE, this);
            case 2: // Search by author
                return new BookSearchScreen(BookSearchService.SearchType.AUTHOR, this);
            case 3: // Search by barcode
                return new BookSearchScreen(BookSearchService.SearchType.BARCODE, this);
            case 4: // Go back
                return getPrevScreen();
            default:
                System.err.println("wut");
        }

        // Hopefully we shouldn't ever reach this point
        return getPrevScreen();
    }
}
