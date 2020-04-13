import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;
import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;
import com.Project0.model.User;
import com.Project0.util.CustReader;
import com.Project0.util.CustWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReadWriteTest {
    CustReader reader = new CustReader();
    CustWriter writer = new CustWriter();
    GolferDAO gdao = new GolferDAOImpl_FileIO();

    @Before
    public void createCustReaderWriter(){
        Assert.assertNotNull(reader);
        Assert.assertNotNull(writer);
        Assert.assertNotNull(gdao);
    }

    /* -----------------   USER TESTS   -------------------*/
    @Test
    public void readUser(){
        User thisUser = reader.readUserLoginRequest("src/main/resources/Users", "Joe", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8");
        Assert.assertNotNull(thisUser);
        System.out.println(thisUser.toString());
    }
    @Test
    public void changeUserPassword(){
        User thisUser = reader.readUserLoginRequest("src/main/resources/Users", "Joe", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8");
        App app = new App();
        app.setPassword("monkey");
        String newPass = app.getPassword();
        try {
            writer.updateUserPassword(thisUser, newPass);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }



    /* -----------------   GOLFER TESTS   -------------------*/
    @Test
    public void createGolfer() {
        Golfer golfer = new Golfer(2, "Joe", "1234 Memory Lane", "1234567890", "9876543210", "Car", "CarModel", "QWE-123");
        try {
            gdao.createGolfer(golfer);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    @Test
    public void readAllGolfers(){
        ArrayList<Golfer> golfers = reader.getGolfers("src/main/resources/Golfers");
        System.out.println("GOLFERS SIZE: " + golfers.size());
        Assert.assertTrue(golfers.size() > 0);
        for (Golfer e : golfers) {
            System.out.println(e.getName());   //SOME REASON THIS THROWS NULLPOINTER EXCEPTION IF USING e.toString()
        }
    }
    @Test
    public void readGolferByName(){
        ArrayList<Golfer> golfers = reader.viewGolferInfo("jo");
        for(Golfer e : golfers) {
            System.out.println("FOUND GOLFER: " + e.toString());
        }
    }
    @Test
    public void updateGolfer() {
        Golfer newGolfer = new Golfer(2L, "Josephine", "3452 Semeaphole", "7892349489", "7892345632", "Ford", "F150", "QTR-345" );
        Golfer oldGolfer = new Golfer(2L, "Joe", "1234 Memory Lane","1234567890","9876543210", "Car", "CarModel","QWE-123");
        try {
            writer.updateGolfer(oldGolfer, newGolfer);
            System.out.println("UPDATE SUCCESSFUL!");
        } catch(Exception e) {
            Assert.assertTrue(false);
        }
    }
    @Test
    public void addGolferLeagueScore() {
        Golfer newGolfer = new Golfer(2L, "Josephine", "3452 Semeaphole", "7892349489", "7892345632", "Ford", "F150", "QTR-345" );
        MatchScore score = new MatchScore(newGolfer, 33, LocalDate.of(2017, 7, 21));
        try {
            gdao.addScoreToHistory(newGolfer, score);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    @Test
    public void readGolferScores() {
        Golfer newGolfer = new Golfer(2L, "Joe", "3452 Semeaphole", "7892349489", "7892345632", "Ford", "F150", "QTR-345" );
        ArrayList<MatchScore> scores = new ArrayList<>();
        try {
            scores = gdao.getGolferScores(newGolfer);
            System.out.printf("SCORES LENGTH: %d", scores.size());;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
