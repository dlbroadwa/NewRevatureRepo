import com.Project0.application.App;
import com.Project0.dao.LeagueDAO;
import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.model.MatchScore;
import com.Project0.services.GolferService;
import com.Project0.services.LeagueService;
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

public class DBTests_League {
    App app;
    @Mock
    LeagueDAO dao;
    @InjectMocks
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
        League league = new League("The Overlords", LocalDate.of(2020,04,13), 8, golfers);
        Mockito.when(dao.createLeague(league)).thenReturn(true);

        boolean successMethod = lService.createLeague(league);
        Assert.assertTrue("ERROR CREATING LEAGUE", successMethod);
    }

    @Test
    public void getAllLeagues() {
        ArrayList<League> leagues = new ArrayList<>();
        leagues.add(league);
        Mockito.when(dao.getAllLeagues()).thenReturn(leagues);

        ArrayList<League> leaguesMocked = new ArrayList<>();
        leaguesMocked = lService.getAllLeagues();
        for(League e: leagues)
            System.out.println(e.toString());
        for(League e: leaguesMocked)
            System.out.println(e.toString());
        Assert.assertEquals(leaguesMocked, leagues);
    }

    @Test
    public void addGolferToLeague() throws Exception {
        //Mockito.doThrow(Exception.class).when(dao).addGolferToLeague(golfer, league);
        Mockito.doNothing().when(dao).addGolferToLeague(golfer, league);

        boolean success = lService.addGolferToLeague(golfer, league);
        Assert.assertTrue("ERROR ADDING GOLFER", success);
    }

    @Test
    public void getLeagueGolfers() {
        Mockito.when(dao.getLeagueGolfers(league)).thenReturn(golfers);

        ArrayList<Golfer> golfersMocked = new ArrayList<>();
        golfersMocked = lService.getLeagueGolfers(league);
        Assert.assertEquals(golfers, golfersMocked);
    }

    @Test
    public void getLeagueScoresOnDay() {
        ArrayList<MatchScore> scores = new ArrayList<>();
        LocalDate day = LocalDate.of(2020, 04, 20);
        scores.add(new MatchScore(golfer, 27, day));
        scores.add(new MatchScore(golfer, 31, day));
        scores.add(new MatchScore(golfer, 33, day));
        Mockito.when(dao.getLeagueScoresOnDay(league, day)).thenReturn(scores);

        ArrayList<MatchScore> scoresMocked = new ArrayList<>();
        scoresMocked = lService.getScoresOnDay(league, day);
        Assert.assertEquals(scores, scoresMocked);
    }
}
