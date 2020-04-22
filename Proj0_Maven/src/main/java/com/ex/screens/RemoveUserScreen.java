package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.services.UserInfoService;

public class RemoveUserScreen implements Screen {
    private final Screen prevScreen;

    public RemoveUserScreen(Screen screen) {
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Delete Patron Account");

        BarcodeReader cardReader = ((LibraryApp)app).getBarcodeReader();

        int cardNumber = 0;
        while (cardNumber == 0) {
            System.out.print("Enter patron's library card number, or press Enter to cancel and go back: ");
            cardNumber = cardReader.readBarcode();
            if (cardNumber == -1)
                return prevScreen;
            else if (cardNumber == 0) {
                System.out.println("Invalid card number, please try again.");
            }
        }

        UserInfoService service = ((LibraryApp)app).getUserInfoService();

        if (service.deleteUser(cardNumber)) {
            System.out.println("Successfully deleted patron account.");
        }
        else {
            System.out.println("Unable to delete patron account!");
            System.out.println("Make sure to check in any books that the patron currently has checked out.");
        }

        return prevScreen;
    }
}
