package com.ex.screens;

import com.ex.io.InputSource;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuScreen implements Screen {
    private String menuTitle;
    private List<String> menuOptions;
    private Screen prevScreen;

    protected void displayMenu() {
        System.out.println(menuTitle);
        for (int i = 0; i < menuOptions.size(); ++i) {
            System.out.println((i+1) + ": " + menuOptions.get(i));
        }
    }
    protected int getMenuChoice(InputSource io) {
        int parsedInput = 0;
        while (parsedInput < 1 || parsedInput > menuOptions.size()) {
            System.out.print("Enter an option: ");
            String input = io.getInput();

            try {
                parsedInput = Integer.parseInt(input);

                if (parsedInput < 1 || parsedInput > menuOptions.size()) {
                    System.out.println("Please enter a number between 1 and " + menuOptions.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option entered.");
            }
        }

        return parsedInput;
    }

    public MenuScreen() {
        menuOptions = new ArrayList<>();
    }
    public MenuScreen(String title) {
        this();
        menuTitle = title;
    }
    public MenuScreen(String title, Screen prevScreen) {
        this(title);
        this.prevScreen = prevScreen;
    }

    public void setMenuTitle(String title) {
        menuTitle = title;
    }
    public void addMenuOption(String text) {
        menuOptions.add(text);
    }

    public Screen getPrevScreen() {
        return prevScreen;
    }
    public void setPrevScreen(Screen screen) {
        prevScreen = screen;
    }
}
