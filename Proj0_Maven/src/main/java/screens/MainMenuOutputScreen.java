package screens;

import app.Application;

public class MainMenuOutputScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        System.out.println("Welcome to Unnamed Library Administration");
        System.out.println("1. Check in book");
        System.out.println("2. Check out book to patron");
        System.out.println("3. Manage library patrons");
        System.out.println("4. Manage book collection");

        return null;
    }
}
