import com.ex.dao.UserDAO;
import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import com.ex.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UserTest {
    private PhoneCarrier carrier = new PhoneCarrier();
    private Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
            "USA", 49341);
    private User user = new User("John", "Sheerin", "7752305812", carrier,
            "john@mail.com", "password", address, 0, true);


    @Mock
    UserDAO dao;

    @InjectMocks
    UserService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new UserService(dao);
//        service = new UserService();
    }

    @Test
    public void hashPassword() {
        String hashedPass = service.hashPassword("password");
        System.out.println(hashedPass);
    }

    @Test
    public void loginUser() {
        User mockedUser = service.loginUser(user);
    }

    @Test
    public void addUser() {
        boolean success = service.addUser(user);
        Assert.assertTrue("testAddUser - failed to create user in dbase", success);
    }

    @Test
    public void displayUser() {
        Mockito.when(dao.displayUser(user)).thenReturn(user);
        Assert.assertNotNull("MOCKED USER IS NULL", service.displayUser(user));
    }

    @Test
    public void updateUser() throws CloneNotSupportedException {
        Mockito.when(dao.displayUser(user)).thenReturn(user);
        Assert.assertNotNull("MOCKED USER IS NULL", service.displayUser(user));

        User newUserInfo = (User)user.clone();
        newUserInfo.setFirstname("BLAH");
        newUserInfo.setLastname("DeBlahBlah");
        boolean success = service.updateUser(user, newUserInfo);
        Assert.assertTrue("updateUser() - NOT UPDATED", success);
    }

    @Test
    public void disableUser() {
        User user = new User();
        user.setEmail("john@mail.com");

        boolean success = service.disableUser(user, true);

        Assert.assertTrue("disableUser() - failed to change inactive state on user" ,user.isInactiveUser());

    }
}
