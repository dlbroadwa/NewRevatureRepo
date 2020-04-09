package ConsoleMenus;

import java.util.Scanner;

public class InputScreen implements Screen {
    private Screen previous;
    public InputScreen(Screen previous) {
        this.previous = previous;
    }
    @Override
    public Screen run(Scanner scan) {

        return previous;
    }
}
