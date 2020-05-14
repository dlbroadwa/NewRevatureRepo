import auction.dataaccess.UserDAO;
import auction.models.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertTrue;

public class testUserDAO {
    @Mock
    private UserDAO userDAO = null;
    private User user = null;
    private String userName;
    private String password = "password";
    private String creditCardInfo;
    private int role;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {

        userName = "bobRose";
        creditCardInfo = "1234567890123456";
        role = 1;
        user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setCreditCardNumber(creditCardInfo);
        user.setRole(role);
    }

    @Test
    public void testSave() {
        userDAO = new UserDAO();
        assertTrue(userDAO.save(user));
    }

    @Test
    public void testDelete() {
        userDAO = new UserDAO();
        assertTrue(userDAO.delete(user));

    }
}
