/**
 * The GuestTest Class tests if the input provided by scanner equals a choice within the array,
 * it is used in the Guest class to relocate to a new Admin class or continue browsing.
 */
package guest;

import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

public class GuestTest
{
    // Tests if the choice matches the choices within the String array.
    @Test
    public void relocateFunctionTest()
    {

        String[] choices = {"browse", "admin"};
        String choice1 = "Browse".toLowerCase();
        Scanner scanner1 = new Scanner(choice1);
        String choice2 = "Admin".toLowerCase();
        Scanner scanner2 = new Scanner(choice2);
        assertEquals(scanner1.next(), choices[0]);
        assertEquals(scanner2.next(), choices[1]);
    }
}
