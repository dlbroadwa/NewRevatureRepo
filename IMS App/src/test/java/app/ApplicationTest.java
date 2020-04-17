package app;

import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Mock
    Application app;

    @Test
    public void getScanner() throws FileNotFoundException {
        File file = new File("resources/testFile.txt");
        Scanner scanner = new Scanner(file);
        assertNotNull(scanner);
    }
}