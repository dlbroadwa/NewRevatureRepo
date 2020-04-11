package screens;

import app.Application;
import app.LibraryApp;

import java.util.Scanner;

public abstract class InputScreen implements Screen {
    Application app;

    public InputScreen(Application app) {
        this.app = app;
    }

    private void promptForInput() {
        System.out.print("Enter an option: ");
    }
    protected int getIntOption() {
        promptForInput();
        Scanner scanner = ((LibraryApp)app).getScanner();
        String input = scanner.nextLine();

        int parsedInput;
        while (true) {
            try {
                parsedInput = Integer.parseInt(input);
                break;
            }
            catch (NumberFormatException ex) {
                System.out.print("Invalid option, please try again: ");
                input = scanner.nextLine();
            }
        }

        return parsedInput;
    }
}
