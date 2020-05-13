import com.ex.dao.UserDAO;
import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import com.ex.services.UserService;
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
//        service = new UserService(dao);
        service = new UserService();

    }

    @Test
    public void loginUser() {
        User mockedUser = service.loginUser("blah@email.com", "1234pass");

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


}
