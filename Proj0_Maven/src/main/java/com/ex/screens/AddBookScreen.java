package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.io.InputSource;
import com.ex.services.BookManagementService;

public class AddBookScreen implements Screen {
    private final Screen prevScreen;

    public AddBookScreen(Screen screen) {
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Add New Book\n");

        BarcodeReader br = ((LibraryApp)app).getBarcodeReader();
        InputSource input = ((LibraryApp)app).getInputSource();

        // Read barcode
        int barcode = 0;
        while (barcode == 0) {
            System.out.print("Enter book barcode, or press Enter to cancel and go back: ");
            barcode = br.readBarcode();
            if (barcode == -1)
                return prevScreen;
            else if (barcode == 0)
                System.out.println("Invalid barcode, please try again.");
        }

        // Read title
        System.out.print("Enter book title: ");
        String title = input.getInput();

        // Read author's name
        System.out.print("Enter author's last name: ");
        String lname = input.getInput();
        System.out.print("Enter author's first name: ");
        String fname = input.getInput();

        // Add the book!
        BookManagementService service = ((LibraryApp)app).getBookManagementService();
        if (service.addNewBook(barcode, title, fname, lname)) {
            System.out.println("Book successfully added to the catalog.");
        }
        else {
            System.out.println("An error occurred while trying to add the new book.");
        }

        return prevScreen;
    }
}
