import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;
import com.Project0.model.Golfer;
import com.Project0.model.User;
import com.Project0.util.CustReader;
import com.Project0.util.CustWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        User thisUser = reader.readUserLoginRequest("src/main/resources/Users", "user", "password");
        Assert.assertNotNull(thisUser);
        System.out.println(thisUser.toString());
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
}