import com.ex.dao.UserDAO;
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


}
