package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.io.InputSource;
import com.ex.models.User;
import com.ex.services.UserInfoService;

public class AddUserScreen implements Screen {
    private final Screen prevScreen;

    public AddUserScreen(Screen screen) {
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Register New User\n");

        BarcodeReader cardReader = ((LibraryApp)app).getBarcodeReader();
        InputSource input = ((LibraryApp)app).getInputSource();

        // Read library card number
        int cardNumber = 0;
        while (cardNumber == 0) {
            System.out.print("Enter library card number, or press Enter to cancel and go back: ");

            cardNumber = cardReader.readBarcode();
            if (cardNumber == -1)
                return prevScreen;
            else if (cardNumber == 0) {
                System.out.println("Invalid card number, please try again.");
            }
        }

        // Read user's name
        System.out.print("Enter user's last name: ");
        String lastName = input.getInput();
        System.out.print("Enter user's first name: ");
        String firstName = input.getInput();

        UserInfoService service = ((LibraryApp)app).getUserInfoService();
        if (service.registerNewUser(cardNumber, firstName, lastName)) {
            System.out.println("Successfully registered new user.");
        }
        else {
            System.out.println("Unable to register user!");
            System.out.println("Check that the user's library card number is unique?");
        }

        return prevScreen;
    }
}
