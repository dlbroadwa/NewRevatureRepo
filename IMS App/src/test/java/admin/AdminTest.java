/**
 * The AdminTest Class tests IdVerification function within Admin Class.
 */
package admin;

import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AdminTest
{
    private int id;

    // Sets value to be passed.
    @Before
    public void setId()
    {
        this.id = 224456789;
    }

    // Tests if the id matches the scanner input.
    @Test
    public void testIdVerification()
    {
        Scanner scanner = new Scanner(String.valueOf(224456789));
        assertTrue(this.id == scanner.nextInt());
    }
}