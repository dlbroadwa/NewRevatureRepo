import com.Project0.application.App;
import com.Project0.dao.LeagueDAO;
import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.services.GolferService;
import com.Project0.services.LeagueService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDate;
import java.util.ArrayList;

public class DBTests_League {
    @Mock
    LeagueDAO dao;
    @Mock
    App app;
    //    @Mock
    LeagueService lService;


    @Rule
    public MockitoRule mockitoRule_LDAO = MockitoJUnit.rule();


    Golfer golfer = new Golfer(2L, "joe", "6765 whistleblower", "9159978987",
            "9159971234", "Toyota", "Camry", "FQZ-123");
    ArrayList<Golfer> golfers = new ArrayList<>();

    League league = new League("The Wizards", LocalDate.of(2020,04,13), 8, golfers);

    @Before
    public void init() {
        app = new App();
        lService = new LeagueService(dao);
        golfers.add(golfer);
    }

    @Test
    public void createLeague() throws Exception {
        Mockito.when(dao.createLeague(league)).thenReturn(true);

        boolean successMethod = lService.createLeague(league);
        Assert.assertEquals(successMethod, dao.createLeague(league));
    }

    @Test
    public void getAllLeagues() {
        ArrayList<League> leagues = new ArrayList<>();
        leagues.add(league);
        Mockito.when(dao.getAllLeagues()).thenReturn(leagues);

        ArrayList<League> leaguesMocked = new ArrayList<>();
        leaguesMocked = lService.getAllLeagues();
        Assert.assertEquals(leaguesMocked, leagues);
    }

    @Test
    public void addGolferToLeague() throws Exception {
        Mockito.doThrow(Exception.class).when(dao).addGolferToLeague(golfer, league);

        boolean success = lService.addGolferToLeague(golfer, league);
    }
}
