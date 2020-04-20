import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;
import com.Project0.services.GolferService;
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

public class DBTests_Golfer {
    App app;
    Golfer golfer = new Golfer(2L, "joe", "6765 whistleblower", "9159978987",
            "9159971234", "Toyota", "Camry", "FQZ-123");
    ArrayList<Golfer> golfers = new ArrayList<>();

    @Mock
    GolferDAO dao;

    @InjectMocks
    GolferService gService;


    @Rule
    public MockitoRule mockitoRule_GDAO = MockitoJUnit.rule();

    @Before
    public void init() {
        app = new App();
        gService = new GolferService(dao);
//        dao = new GolferDAOImpl_DB(app.getConnectionUtil());
        golfers.add(golfer);
    }

    /* ==================    GOLFER TESTS    =================== */

    @Test
    public void createGolfer() {
        Golfer temp = new Golfer(1L, "william", "767 Fairlane Ave.", "6166179939",
                "6166179876", "Porche", "911", "SPN2WIN");
        try {
            Mockito.when(dao.createGolfer(temp)).thenReturn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, gService.createGolfer(temp) );
    }

    @Test
    public void updateGolferInfo() throws Exception {
        Golfer tempOld = new Golfer(1L, "rudy", "1234 memory lane", "6166179939",
                "6166179876", "Ford", "Bronco", "FTW-680");
        Golfer tempNew = new Golfer(1L, "rudy", "6754 Cherrywood Drive", "6166179939",
                "6166179876", "Dodge", "Neon", "TYC-826");

        Mockito.doNothing().when(dao).updateGolferInfo(tempOld, tempNew);
        boolean mockedresult = gService.updateGolfer(tempOld, tempNew);
        Assert.assertTrue("Result was not true", mockedresult);
    }

    @Test
    public void viewGolferInfo() {
        Golfer temp = new Golfer(1L, "rudy", "1234 memory lane", "6166179939",
                "6166179876", "Ford", "Bronco", "FTW-680");
        Mockito.when(dao.viewGolferInfo(golfer)).thenReturn(golfers);

        ArrayList<Golfer> methodGolfers = new ArrayList<>();
        methodGolfers = dao.viewGolferInfo(golfer);
        Assert.assertEquals(golfers, methodGolfers);
    }

    @Test
    public void addScoreToHistory() throws Exception {
        MatchScore tempScore = new MatchScore(golfer, 28, LocalDate.now());

        Mockito.doNothing().when(dao).addScoreToHistory(golfer, tempScore);
        Assert.assertTrue("Service came back false", gService.addGolferScore(golfer, tempScore));
    }

    @Test
    public void getGolferScores() {
        ArrayList<MatchScore> scores = new ArrayList<>();
        MatchScore tempScore = new MatchScore(golfer, 28, LocalDate.now());
        scores.add(tempScore);
        Mockito.when(dao.getGolferScores(golfer)).thenReturn(scores);

        ArrayList<MatchScore> scoresExpected = new ArrayList<>();
        scoresExpected = gService.getGolferScores(golfer);
        for(MatchScore e: scores)
            System.out.println(e.toString());
        for(MatchScore e: scoresExpected)
            System.out.println(e.toString());
        Assert.assertEquals(scoresExpected, scores);
    }

}

