import com.ex.dao.AdminDAO;
import com.ex.dao.PersonDAO;
import com.ex.dao.PersonDAOImpl_PGR;
import com.ex.model.Person;
import com.ex.model.Player;
import com.ex.model.Team;
import com.ex.model.User;
import com.ex.service.AdminService;
import com.ex.service.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class AdminTests {
    User user = new User(1, "dan", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8",
            "walldiv@gmail.com", "admin");

    @Mock
    AdminDAO dao;

    @InjectMocks
    AdminService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
//        service = new AdminService(dao);
        service = new AdminService();
    }

    @Test
    /* Changes the user account permission type level */
    public void changeUserAccessLevel() throws Exception {
        Mockito.doNothing().when(dao).changeUserAccessLevel(user, "blah");
        boolean success = service.changeUserAccessLevel(user, "blah");
        Assert.assertTrue("changeUserAccessLevel - ACCESS LEVEL NOT CHANGED", success);
    }

    @Test
    /* Resets password for user to default password allowing for login/reset */
    public void resetPasswordToDefault() throws Exception {
        Mockito.doNothing().when(dao).resetPasswordToDefault(user);
        boolean success = service.resetPasswordToDefault(user);
        Assert.assertTrue("resetPasswordToDefault - PASSWORD NOT RESET", success);
    }

    @Test
    public void getAllPlayers() {
        PersonService personService = new PersonService();
        List<Player> players = new ArrayList<>();
        players = personService.getAllPlayers();
        for (Player e : players) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void getAllCoaches() {
        PersonService personService = new PersonService();
        List<Person> coaches = new ArrayList<>();
        coaches = personService.getAllCoaches();
        for (Person e : coaches) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void startSeason() throws Exception {
//        Mockito.doNothing().when(dao).createTeam(new Team());
        boolean success = service.startSeason(LocalDate.now());
        Assert.assertTrue("startSeason - UNABLE TO START SEASON", success);
    }

}
