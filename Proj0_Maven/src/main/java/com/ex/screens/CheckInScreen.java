package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.InputSource;

public class CheckInScreen implements Screen {
    Screen prevScreen;

    CheckInScreen() {}
    CheckInScreen(Screen prevScreen) {
        this.prevScreen = prevScreen;
    }
    @Override
    public Screen doScreen(Application app) {
        System.out.println("Book Check-in\n");

        InputSource io = ((LibraryApp)app).getInputSource();

        int barcode = 0;
        boolean scannedBarcode = false;
        while (!scannedBarcode) {
            System.out.print("Scan or enter book barcode, or press Enter to return to the main menu: ");
            String input = io.getInput();
            if (input == null || input.isEmpty())
                return prevScreen;
            try {
                barcode = Integer.parseInt(input);
                scannedBarcode = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid barcode, please try again.");
            }
        }

        // TODO scan in barcode
        System.out.println("checking in " + barcode);
        return this;
    }
}
