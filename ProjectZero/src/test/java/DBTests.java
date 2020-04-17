import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.UserDAO;
import com.Project0.model.Golfer;
import com.Project0.model.User;
import com.Project0.services.GolferService;
import com.Project0.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class DBTests {
    App app;
    UserService uService;
    User user = new User("joe", "password", "user");
    User admin = new User("admin", "password", "admin");
    List<User> users = new ArrayList<>();

    GolferService gService;
    Golfer golfer = new Golfer(2L, "joe", "6765 whistleblower", "9159978987", "9159971234", "Toyota", "Camry", "FQZ-123");
    ArrayList<Golfer> golfers = new ArrayList<>();

    @Mock
    UserDAO dao;
    @Mock
    GolferDAO gdao;

    @Rule
    public MockitoRule mockitoRule_UDAO = MockitoJUnit.rule();
    @Rule
    public MockitoRule mockitoRule_GDAO = MockitoJUnit.rule();

    @Before
    public void init() {
        app = new App();
        uService = new UserService(dao);
        users.add(user);
        users.add(admin);

        gService = new GolferService(gdao);
        golfers.add(golfer);

    }

/* ==================    USER TESTS    =================== */
    @Test
    public void loginSelectedUser() {
        try {
            Mockito.when(dao.loginUser("josephine", "password" , app)).thenReturn(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User temp = uService.svcLoginUser(user, app);
        System.out.println(temp.toString());
        Assert.assertNotNull("Didnt return anyone", temp);
    }

    @Test
    public void changeUserPassword() {
        String tempHashed = app.generateHash("test");
        boolean success = true;
        System.out.printf("NEW HASH PASS: %s", tempHashed);
        try {
            Mockito.when(dao.changeUserPassword(user, tempHashed, app)).thenReturn(success);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean tempSucc = uService.svcChangeUserPassword(user, tempHashed, app);
        System.out.println(tempSucc);
        Assert.assertTrue("FAILED TO CHANGE PASSWORD", tempSucc);    }


/* ==================    GOLFER TESTS    =================== */

    @Test
    public void getSingleGolfer() {
    }
}
