package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.io.InputSource;
import com.ex.models.User;
import com.ex.services.UserInfoService;

public class EditUserInfoScreen implements Screen {
    private final Screen prevScreen;

    // Helper function to retrieve a valid library card number from input
    // (or -1 to signify blank input)
    private int getBarcode(BarcodeReader br, String msg, String errorMsg) {
        int barcode = 0;
        while (barcode == 0) {
            System.out.println(msg);
            barcode = br.readBarcode();
            if (barcode == 0)
                System.out.println(errorMsg);
        }
        return barcode;
    }

    // Gets updated user information from input
    private User getNewUserInfo(User oldUserInfo, Application app) {
        BarcodeReader br = ((LibraryApp)app).getBarcodeReader();
        InputSource input = ((LibraryApp)app).getInputSource();

        User newInfo = new User(oldUserInfo.getCardNumber(), oldUserInfo.getFirstName(),
                oldUserInfo.getLastName());

        // Set new barcode
        int newBarcode = getBarcode(br,
                "Enter new library card number (blank to leave unchanged): ",
                "Invalid card number, please try again.");
        if (newBarcode != -1)
            newInfo.setCardNumber(newBarcode);

        // Set new first name
        System.out.print("Enter new first name (blank to leave unchanged): ");
        String name = input.getInput();
        if (!(name == null || name.equals(""))) {
            newInfo.setFirstName(name);
        }

        // Set new last name
        System.out.print("Enter new last name (blank to leave unchanged): ");
        name = input.getInput();
        if (!(name == null || name.equals(""))) {
            newInfo.setLastName(name);
        }

        return newInfo;
    }
    public EditUserInfoScreen(Screen screen) {
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        System.out.println("Edit User Information\n");
        BarcodeReader reader = ((LibraryApp)app).getBarcodeReader();

        int barcode = getBarcode(reader,
                "Enter or scan library card, or press Enter to go back: ",
                "Invalid card number, please try again.");

        if (barcode == -1)
            return prevScreen;

        UserInfoService service = ((LibraryApp)app).getUserInfoService();
        User userInfo = service.getUserInfo(barcode);

        if (userInfo != null) {
            User newInfo = getNewUserInfo(userInfo, app);

            if (service.updateUserInfo(userInfo.getCardNumber(), newInfo)) {
                System.out.println("User information updated successfully.");
            }
            else {
                System.out.println("An error occurred when trying to update user information!");
            }
        }
        else {
            System.out.println("User not found!\n");
        }

        return prevScreen;
    }
}
