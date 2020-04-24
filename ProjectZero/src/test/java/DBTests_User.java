import com.Project0.application.App;
import com.Project0.dao.UserDAO;
import com.Project0.model.User;
import com.Project0.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

public class DBTests_User {
    User user = new User("joe", "password", "user");
    User admin = new User("admin", "password", "admin");
    List<User> users = new ArrayList<>();

    @InjectMocks
    UserService uService;

    @Mock
    UserDAO dao;
//    @Mock
    App app;

    @Rule
    public MockitoRule mockitoRule_UDAO = MockitoJUnit.rule();

    @Before
    public void init() {
        app = new App();
        uService = new UserService(dao);
        users.add(user);
        users.add(admin);
    }

/* ==================    USER TESTS    =================== */
    @Test
    public void loginSelectedUser() {
        try {
            Mockito.when(dao.loginUser("joe", "password" , app)).thenReturn(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User temp = uService.svcLoginUser(user, app);
        System.out.println(temp.toString());
        Assert.assertNotNull(temp);
    }

    @Test
    public void changeUserPassword() {
        String tempHashed = app.generateHash("test");
        String tempHashed2 = app.generateHash("toy");
        System.out.printf("NEW HASH PASS: %s \n", tempHashed);
        try {
            Mockito.when(dao.changeUserPassword(user, tempHashed, app)).thenReturn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean tempSucc = uService.svcChangeUserPassword(user, tempHashed, app);
        System.out.println(tempSucc);
        Assert.assertTrue("FAILED TO CHANGE PASSWORD", tempSucc);    }
}
