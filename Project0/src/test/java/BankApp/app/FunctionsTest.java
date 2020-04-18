package BankApp.app;

import BankApp.DAO.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Functions.class, UserAuthentication.class})


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

        PowerMockito.mockStatic(UserAuthentication.class);
        Mockito.when(UserAuthentication.getUserId()).thenReturn(1);

        UserDAO userDao = Mockito.mock(UserDAO.class);

        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDao);
        Mockito.when(userDao.updateAmount(Mockito.anyInt(), Mockito.anyFloat(), Mockito.anyBoolean())).thenReturn(true);

        InputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);

        Functions functions = new Functions();
        functions.deposit();
        System.out.println("Test 1");

        Mockito.verify(userDao, Mockito.times(1)).updateAmount(Mockito.anyInt(), Mockito.anyFloat(), Mockito.anyBoolean());


    }
    //Test 2
    @Test
    public void testDepositPositive() throws Exception {

        PowerMockito.mockStatic(UserAuthentication.class);
        Mockito.when(UserAuthentication.getUserId()).thenReturn(1);

        UserDAO userDao = Mockito.mock(UserDAO.class);

        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDao);
        Mockito.when(userDao.updateAmount(Mockito.anyInt(), Mockito.anyFloat(), Mockito.anyBoolean())).thenReturn(true);

        InputStream in = new ByteArrayInputStream("100".getBytes());
        System.setIn(in);

        Functions functions = new Functions();
        System.out.println("Test 2");
        functions.deposit();

        Mockito.verify(userDao, Mockito.times(1)).updateAmount(Mockito.anyInt(), Mockito.anyFloat(), Mockito.anyBoolean());


    }

}
