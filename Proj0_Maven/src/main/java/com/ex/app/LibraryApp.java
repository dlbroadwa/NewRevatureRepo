package com.ex.app;

import com.ex.io.ConsoleInputSource;
import com.ex.io.InputSource;
import com.ex.screens.MainMenu;
import com.ex.screens.Screen;
import com.ex.utils.DatabaseConnection;

public class LibraryApp implements Application {
    private DatabaseConnection databaseConnection;
    private InputSource inputSource;
    private Screen screen;

    public LibraryApp(DatabaseConnection dc) {
        databaseConnection = dc;
        inputSource = new ConsoleInputSource();
        screen = new MainMenu();
    }

    @Override
    public void run() {
        while (screen != null)
            screen = screen.doScreen(this);
    }

    public InputSource getInputSource() {
        return inputSource;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }
}
