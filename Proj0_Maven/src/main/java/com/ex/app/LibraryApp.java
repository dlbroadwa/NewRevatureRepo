package app;

import io.ConsoleInputSource;
import io.InputSource;
import screens.MainMenuScreen;
import screens.Screen;

import java.util.Scanner;

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
