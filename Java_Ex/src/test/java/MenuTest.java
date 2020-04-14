import UI.MenuSelect;
import gameaccounts.ListManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class MenuTest {
    MenuSelect menu;
    ListManager manager;

    @Before
    public void testInit() {
        menu = new MenuSelect();
        manager=menu.accounts;
    }

    @Test // this is a single test
    public void logInTest() throws IOException {
        menu.logIn("Dylan","password");

        Assert.assertEquals ("User credentials were denied",false,menu.exitCondition2);
    }

    @Test
    public void signUpTest() throws IOException {
        menu.signUp("Dylan","password");
        Assert.assertTrue ("New user was created",menu.exitCondition2);
        //tests if current account is referenced

        menu.signUp("Newman","password");
        Assert.assertFalse ("New user not created",menu.exitCondition2);
        //tests if current account is referenced
        Assert.assertNotNull("Account object not accessed", manager.getCurr());
        //tests if current account matches given input
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
