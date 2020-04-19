package BankApp.app;

import BankApp.controller.AuthenticationController;
import BankApp.controller.UserController;
import BankApp.dao.user.impl.DefaultUserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserController.class, AuthenticationController.class})


/**********
 *
 * 1. test positive deposit
 * 2. test 0 on deposit
 * 3. withdraw positive
 * 4. withdraw negative
 * 5. withdraw 0
 * 6. balance
 * 7. Main menu test deposit method
 * 8. main menu test withdraw method
 * 9. test deposit method in main menu
 * 10. Mock connection in userdao and get connection object.  validate userid and password
 * look example mocking connection object
 */
public class FunctionsTest {

    //Test 1
    @Test
    public void testDepositZero() throws Exception {

        PowerMockito.mockStatic(UserController.class);
        DefaultUserDao userDao = Mockito.mock(DefaultUserDao.class);
        PowerMockito.whenNew(DefaultUserDao.class).withNoArguments().thenReturn(userDao);

        UserController userController = new UserController(userDao);
        boolean result = userController.deposit(1,0);
        Assert.assertEquals(result,false);


    }


    //Test 2
    @Test
    public void testDepositPositive() throws Exception {

        PowerMockito.mockStatic(UserController.class);
        DefaultUserDao userDao = Mockito.mock(DefaultUserDao.class);
        PowerMockito.whenNew(DefaultUserDao.class).withNoArguments().thenReturn(userDao);

        UserController userController = new UserController(userDao);
        boolean result = userController.deposit(1,100);
        Assert.assertEquals(result,true);

    }

}
