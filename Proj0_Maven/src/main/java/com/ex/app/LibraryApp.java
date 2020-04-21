package com.ex.app;

import com.ex.dao.BookDAO;
import com.ex.dao.UserDAO;
import com.ex.io.*;
import com.ex.screens.MainMenu;
import com.ex.screens.Screen;
import com.ex.services.BookSearchService;
import com.ex.services.CheckInService;
import com.ex.services.CheckOutService;
import com.ex.utils.DatabaseConnection;

public class LibraryApp implements Application {
    private DatabaseConnection databaseConnection;
    private InputSource inputSource;
    private BarcodeReader barcodeReader;
    private Screen screen;

    // Data access stuff
    BookDAO bookData;
    UserDAO userData;
    // Services
    CheckInService checkInService;
    CheckOutService checkOutService;
    BookSearchService bookSearchService;

    public LibraryApp(DatabaseConnection dc) {
        databaseConnection = dc;
        inputSource = new ConsoleInputSource();
        barcodeReader = new BarcodeReader(inputSource);
        screen = new MainMenu();

        bookData = new BookSQLDatabase(databaseConnection);
        userData = new UserSQLDatabase(databaseConnection);

        checkInService = new CheckInService(bookData);
        checkOutService = new CheckOutService(bookData);
        bookSearchService = new BookSearchService(bookData);
    }

    @Override
    public void run() {
        while (screen != null)
            screen = screen.doScreen(this);
    }

    public InputSource getInputSource() {
        return inputSource;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public BarcodeReader getBarcodeReader() {
        return barcodeReader;
    }

    public CheckInService getCheckInService() {
        return checkInService;
    }
    public CheckOutService getCheckOutService() {
        return checkOutService;
    }

    public BookSearchService getBookSearchService() {
        return bookSearchService;
    }
}
