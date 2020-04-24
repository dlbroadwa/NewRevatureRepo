/**
 * The IMSEntryTest tests the run method with IMSEntry.
 */
package app;

import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.*;

public class IMSEntryTest {

    String choice1 = "1";
    String choice2 = "2";
    String choice3 = "3";
    Scanner scanner;

    // Scanner takes in input and determines if they are equal to the choices within the array.
    @Test
    public void run()
    {
        String[] choices = {"1", "2", "3"};
        scanner = new Scanner(choice1);
        this.choice1 = scanner.next().toLowerCase();
        assertEquals(choices[0], this.choice1);
        scanner = new Scanner(choice2);
        assertEquals(choices[1], choice2);
        scanner = new Scanner(choice3);
        assertEquals(choices[2], choice3);
    }
}