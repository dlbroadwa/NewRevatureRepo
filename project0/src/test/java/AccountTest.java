import com.game.app.GameAccountApplication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    
    private GameAccountApplication gameAccountApplication;
    
    @Before
    public void testInit() {
        gameAccountApplication = new GameAccountApplication();
        gameAccountApplication.start();
    }

    @Test // this is a single test
    public void logInTest() {
        //password not correct
        gameAccountApplication.getAccountService().checkCredentials("dylan", "password1");
        Assert.assertNull ("User credentials passed",
                gameAccountApplication.getAccountService().getCurr());

        //user does not exist
        gameAccountApplication.getAccountService().checkCredentials("bob", "password");
        Assert.assertNull ("User credentials passed",
                gameAccountApplication.getAccountService().getCurr());

        //user login pass
        gameAccountApplication.getAccountService().checkCredentials("dylan", "password");
        Assert.assertEquals("User credentials were denied", "dylan",
                gameAccountApplication.getAccountService().getCurr().getName());
    }

    @Test
    public void signUpTest(){
        //creates new account
        Assert.assertTrue ("New user was not created",
                gameAccountApplication.getAccountService().signUp("test","test"));

        //current account exists and should not pass
        Assert.assertFalse ("New account creation succeeded",
                gameAccountApplication.getAccountService().signUp("test","test"));

        //current account exists and should not pass
        Assert.assertFalse ("New account creation succeeded",
                gameAccountApplication.getAccountService().createAccount("test","test", true));

        gameAccountApplication.getAccountService().closeAccount();

        //creates new admin account and should pass
        Assert.assertTrue ("New user was not created",
                gameAccountApplication.getAccountService().createAccount("test2","test", true));
        //current account referenced is Newman2 so it should close
        gameAccountApplication.getAccountService().closeAccount();
    }

    @Test
    public void menuDeleteTest(){
        gameAccountApplication.getAccountService().signUp("test","test");
        //log in to account necessary for these operations
        gameAccountApplication.getAccountService().createAccount("test2","test", true);
        gameAccountApplication.getAccountService().checkCredentials("test2", "test");

        boolean temp;
        //deleted account does not exist so it should print out no account found
        temp=gameAccountApplication.getAccountService().deleteAccount("Godzilla");
        Assert.assertFalse("Godzilla Account was deleted by mistake", temp);

        //should delete account, method should return true
        temp=gameAccountApplication.getAccountService().deleteAccount("test");
        Assert.assertTrue("Account was not deleted by mistake", temp);

        //should not delete admin account
        temp=gameAccountApplication.getAccountService().deleteAccount("Dylan");
        Assert.assertFalse("Account was deleted by mistake", temp);

        gameAccountApplication.getAccountService().closeAccount();
    }

    @Test
    public void creditTest(){
        //log in to account necessary for these operations
        gameAccountApplication.getAccountService().checkCredentials("dylan", "password");
        int x = gameAccountApplication.getAccountService().getCurr().getBalance();

        //more than what the account has is requested
        Assert.assertFalse("You manage to withdraw more than what you have",
                gameAccountApplication.getAccountService().spendC(x+1));

        Assert.assertTrue("You manage to withdraw everything from your account",
                gameAccountApplication.getAccountService().spendC(x));

        //check if the account is empty
        Assert.assertEquals("You do not have 0 in your account",
                gameAccountApplication.getAccountService().getCurr().getBalance(),0);

        //check if balance is back to the original
        gameAccountApplication.getAccountService().depositM(x);
        Assert.assertEquals("Your balance has not been restored",
                gameAccountApplication.getAccountService().getCurr().getBalance(),x);

        //gifting does not need to be tested as it is derived from above functions
    }


}
