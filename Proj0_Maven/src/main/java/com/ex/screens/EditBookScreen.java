package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.io.InputSource;
import com.ex.models.Book;
import com.ex.services.BookManagementService;
import com.ex.services.BookSearchService;

public class EditBookScreen implements Screen {
    private final Screen prevScreen;

    private int getBarcode(BarcodeReader br, String msg, String errMsg) {
        int barcode = 0;
        while (barcode == 0) {
            System.out.print(msg);
            barcode = br.readBarcode();
            if (barcode == 0)
                System.out.println(errMsg);
        }
        return barcode;
    }

    private Book getNewBookInfo(Book oldInfo, Application app) {
        BarcodeReader br = ((LibraryApp)app).getBarcodeReader();
        InputSource input = ((LibraryApp)app).getInputSource();

        // Make a copy of the old book
        // (I probably should've just overridden Object.clone())
        Book newBook = new Book(oldInfo.getBarcode(), oldInfo.getTitle(),
                oldInfo.getAuthorFirstName(), oldInfo.getAuthorLastName());
        newBook.setCheckedOutUser(oldInfo.getCheckedOutUser());
        newBook.setDueDate(oldInfo.getDueDate());

        // Get new book info
        int newBarcode = getBarcode(br, "Enter new barcode (blank to leave unchanged): ",
                "Invalid barcode, please try again.");
        if (newBarcode != -1)
            newBook.setBarcode(newBarcode);

        System.out.print("Enter new title (blank to leave unchanged): ");
        String newTitle = input.getInput();
        if (!(newTitle == null || newTitle.equals("")))
            newBook.setTitle(newTitle);

        System.out.print("Enter new author's last name (blank to leave unchanged): ");
        String newAuthorLName = input.getInput();
        if (!(newAuthorLName == null || newAuthorLName.equals("")))
            newBook.setAuthorLastName(newAuthorLName);

        System.out.print("Enter new author's first name (blank to leave unchanged): ");
        String newAuthorFName = input.getInput();
        if (!(newAuthorFName == null || newAuthorFName.equals("")))
            newBook.setAuthorFirstName(newAuthorFName);

        return newBook;
    }

    /**
     * Constructs an "Edit Book Information" menu.
     * @param screen the screen that created this screen
     */
    public EditBookScreen(Screen screen) {
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Edit Book Information\n");
        BarcodeReader reader = ((LibraryApp)app).getBarcodeReader();

        int barcode = getBarcode(reader,
                "Enter or scan book barcode, or press Enter to go back: ",
                "Invalid barcode, please try again.");
        if (barcode == -1)
            return prevScreen;

        BookSearchService searchService = ((LibraryApp)app).getBookSearchService();
        BookManagementService editService = ((LibraryApp)app).getBookManagementService();
        Book oldBook = searchService.executeSearch(barcode);
        if (oldBook != null) {
            Book newBook = getNewBookInfo(oldBook, app);

            if (editService.updateBookInfo(barcode, newBook)) {
                System.out.println("Book information updated successfully.");
            }
            else {
                System.out.println("An error occurred while trying to update book information.");
            }
        }
        else {
            System.out.println("Unrecognized book " + barcode + "!");
        }

        return prevScreen;
    }
}
