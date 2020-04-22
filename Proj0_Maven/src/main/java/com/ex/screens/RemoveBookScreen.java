package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.services.BookManagementService;

public class RemoveBookScreen implements Screen {
    private final Screen prevScreen;

    public RemoveBookScreen(Screen screen) {
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Remove Book From Catalog");

        BarcodeReader reader = ((LibraryApp)app).getBarcodeReader();

        int barcode = 0;
        while (barcode == 0) {
            System.out.print("Enter or scan barcode, or press Enter to go back: ");
            barcode = reader.readBarcode();
            if (barcode == -1)
                return prevScreen;
            else if (barcode == 0)
                System.out.println("Invalid barcode, please try again.");
        }

        BookManagementService service = ((LibraryApp)app).getBookManagementService();
        if (service.removeBook(barcode)) {
            System.out.println("Book successfully removed from the catalog.");
        }
        else {
            System.out.println("Unable to remove the book from the catalog!");
            System.out.println("Make sure that it is not checked out before attempting to remove it.");
        }

        return prevScreen;
    }
}
