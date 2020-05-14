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
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UserTest {

    @Mock
    UserDAO dao;

    @InjectMocks
    UserService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
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
        User mockedUser = service.loginUser("blah@email.com", "password");
    }

    @Test
    public void addUser() {
        UserService userService = new UserService();

        PhoneCarrier carrier = new PhoneCarrier();
        Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
                "USA", 49341);
        User user = new User("John", "Sheerin", "7752305812", carrier,
                "john@mail.com", "password", address, 0, true);

        boolean success = userService.addUser(user);
        Assert.assertTrue("testAddUser - failed to create user in dbase", success);
    }

    @Test
    public void displayUser() {
//        Address address = new Address(13, "Delores", "apt 5", "Truckee", "CA", "USA", 66666);
//        User user = new User("John", "Sheerin", "7752305812",
//                new PhoneCarrier(), "john@mail.com", "password", address, 13, true);
        User user = new User();
        user.setEmail("john@mail.com");
        User tmp = service.displayUser(user);
    }

    @Test
    public void updateUser() throws CloneNotSupportedException {
        User user = new User();
        user.setEmail("john@mail.com");
        User origUser = service.displayUser(user);
        Assert.assertNotNull("updateUser() - DBase Getter for user is null", origUser);

        User newUserInfo = (User)origUser.clone();
        newUserInfo.setFirstname("BLAH");
        newUserInfo.setLastname("DeBlahBlah");
        boolean success = service.updateUser(origUser, newUserInfo);
        Assert.assertTrue("updateUser() - NOT UPDATED", success);
    }

    @Test
    public void disableUser() {
        User user = new User();
        user.setInactiveUser(true);

        Assert.assertTrue("User is still enabled" ,user.isInactiveUser());
        System.out.println("User has been disabled");
    }
}
