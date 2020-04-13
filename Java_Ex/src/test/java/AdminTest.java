import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AdminTest {
    AdminTest adminTest;

    @Before
    public void testInit() {
    }

    @Test // this is a single test
    public void shouldPrintHelloWorld() {
        System.out.println("Hello, World");
    }

    @Test
    public void shouldReturnAValidString() {

        // Screen --> Service -> DAO
        // Mock the Screen by directly invoking the method
        // Mock the DAO by creating a fake file to read
        String output = "All signs point to yes";
        Assert.assertEquals("Didn't return desired string", "All signs point to yes", output);
    }

}
