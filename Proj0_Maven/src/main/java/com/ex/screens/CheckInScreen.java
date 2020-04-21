package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.models.Book;
import com.ex.services.CheckInService;

public class CheckInScreen implements Screen {
    Screen prevScreen;

    public CheckInScreen() {}
    public CheckInScreen(Screen prevScreen) {
        this.prevScreen = prevScreen;
    }

    private void doCheckIn(Application app, int barcode) {
        CheckInService cis = ((LibraryApp)app).getCheckInService();
        Book checkedInBook = cis.checkIn(barcode);
        if (checkedInBook != null) {
            // Display book information
            System.out.println("Check-in successful: " + checkedInBook.getBarcode());
            System.out.println(checkedInBook.getTitle());
            System.out.println(checkedInBook.getAuthor());
        }
        else {
            System.out.println("Error: Unable to find or check in any book with barcode " + barcode);
        }
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Book Check-in\n");

        BarcodeReader br = ((LibraryApp)app).getBarcodeReader();

        int barcode = 0;
        while (barcode == 0) {
            System.out.print("Scan or enter book barcode, or press Enter to return to go back: ");

            barcode = br.readBarcode();
            if (barcode == -1)
                return prevScreen;

            if (barcode == 0) {
                System.out.println("Invalid barcode, please try again.");
            }
        }

        doCheckIn(app, barcode);

        return this;
    }
}
