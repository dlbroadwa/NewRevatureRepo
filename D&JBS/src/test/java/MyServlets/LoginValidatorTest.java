package MyServlets;

import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;


class LoginValidatorTest {


    private Scanner scanner;
    private String username = "johnny.admin";
    private String password = "JellyBean13";
    private boolean found = false;



    @Test
    public static int testUserInput(InputStream in, PrintStream out) {
        Scanner keyboard = new Scanner(in);
        out.println("Give a number between 1 and 10");
        int input = keyboard.nextInt();

        while (input < 1 || input > 10) {
            out.println("Wrong number, try again.");
            input = keyboard.nextInt();
        }

        return input;
    }

    String tempUsername = "johnny.admin";
    String tempPassword = "JellyBean13";

    @Test
    public void checkUsername() {


        if (tempUsername.trim().equals(username.trim())) {
            found = true;
        }
        assertTrue(found);
    }

    @Test
    public void checkPassword() {


        if (tempPassword.trim().equals(password.trim())) {
            found = true;
        }
        assertTrue(found);
    }


    @Test
    void doPost() {
        boolean emailCheck = false;
        String email = "johnjohn@google.com";
        if(email.length() != 0 && email.equals("")){
            emailCheck = true;
            assertTrue(emailCheck);
        }

    }
}
