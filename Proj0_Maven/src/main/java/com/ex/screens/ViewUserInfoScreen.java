package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.models.Book;
import com.ex.models.User;
import com.ex.services.UserInfoService;

import java.util.List;

public class ViewUserInfoScreen implements Screen {
    private final Screen prevScreen;

    private void displayUserInfo(User user, List<Book> books) {
        System.out.println("Name: " + user.getName());
        System.out.println("Library card number: " + user.getCardNumber());
        if (books.isEmpty()) {
            System.out.println("\nChecked out books: None\n");
        }
        else {
            System.out.println("\nChecked out books:\n");
            for (Book b: books) {
                System.out.println(b.getTitle());
                System.out.println("Author: " + b.getAuthor());
                System.out.println("Barcode: " + b.getBarcode());
                System.out.println("Due date: " + b.getDueDate().toString() + "\n");
            }
        }
    }
    public ViewUserInfoScreen(Screen screen) {
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("View Patron Information\n");
        BarcodeReader reader = ((LibraryApp)app).getBarcodeReader();

        int cardNumber = 0;

        while (cardNumber == 0) {
            System.out.print("Enter or scan library card number, or press Enter to go back: ");
            cardNumber = reader.readBarcode();

            if (cardNumber == -1) {
                return prevScreen;
            }
            else if (cardNumber == 0) {
                System.out.println("Invalid card number, please try again.");
            }
        }

        UserInfoService service = ((LibraryApp)app).getUserInfoService();
        User userInfo = service.getUserInfo(cardNumber);
        List<Book> userBooks = service.getCheckedOutBooks(cardNumber);

        displayUserInfo(userInfo, userBooks);

        return prevScreen;
    }
}
