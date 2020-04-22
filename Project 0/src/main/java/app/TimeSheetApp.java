package app;

import menu.MainMenu;
import menu.Menu;

import java.util.Scanner;

/**
 * This class calls in the scanner to read user input to be used with menu selection.
 * It also generates the menu screen.
 */
public class TimeSheetApp extends Application{
    private Menu currentMenu = null;
    private Scanner scanner;


    public TimeSheetApp(){
        this.scanner = new Scanner(System.in); //reads input
        currentMenu = new MainMenu();
    }

    @Override
    public void runApp() {
        while(currentMenu != null) {
            currentMenu = currentMenu.makeMenu(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

}
