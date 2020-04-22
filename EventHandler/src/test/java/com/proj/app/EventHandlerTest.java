package com.proj.app;

import com.proj.clients.UserServices;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.*;

//*****************************************EventHandler Tests****************************************************//
public class EventHandlerTest {

    /**
     * This test, tests the scanner against a saved file.
     * If the scanner finds info inside the file it returns not null, passing the test.
     * @throws FileNotFoundException throws the exception if file is not found, however still returning null.
     */
    @Test
    public void getScanner()throws FileNotFoundException {
        File user = new File("resources/login");
        Scanner scanner = new Scanner(user);
        assertNotNull(scanner);
    }



    @Test
    public void scanner()
    {
        Scanner scanner;
        String opt1 = "hi-there";
        String opt2 = "hey-there";
        String opt3 = "ho-there";

        String[] testOpt = {"hi-there", "hey-there", "ho-there"};
        scanner = new Scanner(opt1);
        assertEquals(testOpt[0], opt1);

        scanner = new Scanner(opt2);
        assertEquals(testOpt[1], opt2);

        scanner = new Scanner(opt3);
        assertEquals(testOpt[2], opt3);
    }

}