import com.game.app.GameAccountApplication;
import com.game.screens.EntryScreen;
import com.game.screens.MenuScreen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MenuTest {
    
    private GameAccountApplication gameAccountApplication;
    
    @Before
    public void testInit() {
        gameAccountApplication = new GameAccountApplication();
        gameAccountApplication.start();
    }

    @Test // this is a single test
    public void logInTest() {
        ((EntryScreen)gameAccountApplication.getScreen()).testInit(gameAccountApplication);

        //password not correct
        ((EntryScreen)gameAccountApplication.getScreen()).logIn("dylan", "password1");
        Assert.assertFalse ("User credentials passed",((EntryScreen)gameAccountApplication.getScreen()).exitCondition);

        //user does not exist
        ((EntryScreen)gameAccountApplication.getScreen()).logIn("bob", "password");
        Assert.assertFalse ("User credentials passed",((EntryScreen)gameAccountApplication.getScreen()).exitCondition);

        //user login pass
        ((EntryScreen)gameAccountApplication.getScreen()).logIn("dylan", "password");
        Assert.assertTrue("User credentials were denied", ((EntryScreen)gameAccountApplication.getScreen()).exitCondition);
    }

    @Test
    public void signUpTest(){
        //current account exists and should not pass
        ((EntryScreen)gameAccountApplication.getScreen()).signUp("Dylan","password");
        Assert.assertFalse ("New account creation succeeded",((EntryScreen)gameAccountApplication.getScreen()).exitCondition);

        //creates new account
        ((EntryScreen)gameAccountApplication.getScreen()).signUp("Newman","password");
        Assert.assertTrue ("New user was created",((EntryScreen)gameAccountApplication.getScreen()).exitCondition);
    }

    @Test
    public void menuDeleteTest(){
        gameAccountApplication.setScreen(new MenuScreen());
        //initiates necessary parameters for tests
        ((MenuScreen)gameAccountApplication.getScreen()).testInit(gameAccountApplication);

        //delete account
        //((MenuScreen)gameAccountApplication.getScreen()).

    }
}
