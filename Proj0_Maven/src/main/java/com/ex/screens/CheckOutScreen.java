package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.models.Book;
import com.ex.services.CheckOutService;

public class CheckOutScreen implements Screen {
    private Screen prevScreen;

    public CheckOutScreen() {}
    public CheckOutScreen(Screen prevScreen) {
        this.prevScreen = prevScreen;
    }

    private int getBarcode(BarcodeReader br, String prompt, String errorMsg) {
        int barcode = 0;
        while (barcode == 0) {
            System.out.print(prompt);
            barcode = br.readBarcode();
            if (barcode == 0)
                System.out.println(errorMsg);
        }
        return barcode;
    }

    private void doCheckOut(Application app, int user, int book) {
        CheckOutService cos = ((LibraryApp)app).getCheckOutService();
        Book checkedOutBook = cos.checkOut(book, user);
        if (checkedOutBook != null) {
            // Display book information
            System.out.println("Check-out successful: " + checkedOutBook.getBarcode());
            System.out.println(checkedOutBook.getTitle());
            System.out.println(checkedOutBook.getAuthor());

            // Display ~~user~information~and~~ due date
            System.out.println("Due date: " + checkedOutBook.getDueDate().toString() + "\n");
        }
        // CheckOutService handles the failure state output for us, so no need for this branch anymore
        /*else {
            System.out.println("Error: Could not check out book " );
        }*/
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Book Check-out");

        BarcodeReader br = ((LibraryApp)app).getBarcodeReader();

        int libraryCardNum = getBarcode(br,
                "Enter or scan patron's library card number, or press Enter to go back: ",
                "Invalid card number, please try again.");
        if (libraryCardNum == -1)
            return prevScreen;

        int bookBarcode = getBarcode(br,
                "Enter or scan book barcode, or press Enter to cancel and go back: ",
                "Invalid barcode, please try again.");
        if (bookBarcode == -1)
            return prevScreen;

        doCheckOut(app, libraryCardNum, bookBarcode);

        return prevScreen;
    }
}
