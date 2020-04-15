package com.ex.app;

import com.ex.io.ConsoleInputSource;
import com.ex.io.InputSource;
import com.ex.screens.MainMenuScreen;
import com.ex.screens.Screen;

public class LibraryApp implements Application {
    private InputSource inputSource;
    private Screen screen;

    public LibraryApp() {
        inputSource = new ConsoleInputSource();
        screen = new MainMenuScreen();
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

    public Screen getPrevScreen() {
        return screen;
    }
}
