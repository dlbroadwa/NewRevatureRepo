import com.game.app.GameAccountApplication;

import com.game.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MessageTest {
    private GameAccountApplication gameAccountApplication;
    private AccountService accountService;

    @Before
    public void testInit() {
        gameAccountApplication = new GameAccountApplication();
        gameAccountApplication.start();
        accountService = gameAccountApplication.getAccountService();
    }

    @Test
    public void testSendMessages(){

        if(!accountService.signUp("test","test")){
            accountService.checkCredentials("test", "test");
        }

        //tests send to myself, should return true
        Assert.assertTrue("Message was not sent",accountService.send("test", "Hello Me"));

        //account does not exist, should return false
        Assert.assertFalse("Message was sent",accountService.send("Test", "Hello Me"));

        //delete 2nd index of message, should return false
        Assert.assertFalse("Somehow, nonexistent message was deleted",accountService.delete(2));

        //delete 1st index of message, should return true
        Assert.assertTrue("Message was not deleted",accountService.delete(1));

        accountService.send("test", "Hello Me");
        accountService.send("test", "Hello Me");
        accountService.send("test", "Hello Me");

        //test to see if it returns the number of messages
        Assert.assertEquals("You do not have 3 messages",accountService.getMessageNumber(),3);
        accountService.deleteAll();
        Assert.assertEquals("You do not have 0 messages",accountService.getMessageNumber(),0);

        accountService.closeAccount();
    }

    @Test
    public void testMessageAdmin(){
        if(!accountService.createAccount("testAdmin","test", true)){
            accountService.checkCredentials("testAdmin", "test");
        }

        if(!accountService.signUp("test","test")){
            accountService.checkCredentials("test", "test");
        }

        Assert.assertTrue("Message was not sent",accountService.send("testAdmin", "Hello You"));
        Assert.assertEquals("You do not have 0 messages",accountService.getMessageNumber(),0);

        accountService.closeAccount();
        accountService.checkCredentials("testAdmin","test");
        Assert.assertEquals("You do not have 0 messages",accountService.getMessageNumber(),1);
        accountService.closeAccount();

    }

}
