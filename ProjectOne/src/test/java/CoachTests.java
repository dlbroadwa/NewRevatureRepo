import com.ex.dao.CoachDAO;
import com.ex.model.*;
import com.ex.service.CoachService;
import com.sun.deploy.util.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoachTests {
    Sponsor sponsor = new Sponsor("Meijers", "616-867-5309", "fred@meijer.com");
    List<Player> players = new ArrayList<>();
    List<Schedule> schedules = new ArrayList<>();
    List<GameScore> scores = new ArrayList<>();
    Person coach = new Person("BillyBob Thornton", "616-458-1234", "616-458-1234", PhoneCarrier.TMobile, true, new Team());
    Team team = new Team("Bad News Bears", coach, players, schedules, scores, null);

    @Mock
    CoachDAO dao;

    @InjectMocks
    CoachService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        service = new CoachService();
        coach.setTeam(team);
    }

    @Test
    public void addSponsor() throws Exception {
        Mockito.doNothing().when(dao).addSponsor(sponsor, team);
        boolean success = service.addSponsor(sponsor, team);
        Assert.assertTrue("ADD SPONSOR FAILURE", success);
    }

    @Test
    public void setTeamPracticeDay() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        Mockito.doNothing().when(dao).setPracticeDay(date, team);
        boolean success = service.setPracticeDay(date, team);
        Assert.assertTrue("ADD PRACTICEDAY FAILURE", success);
    }

    @Test
    public void createDate() {
        LocalDateTime date = LocalDateTime.now();
        Timestamp time = Timestamp.valueOf(date);
        System.out.println(time.toString());
    }
}
