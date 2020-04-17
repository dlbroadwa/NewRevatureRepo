import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
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

public class DBTests_Golfer {
    App app;
    GolferService gService;
    Golfer golfer = new Golfer(2L, "joe", "6765 whistleblower", "9159978987",
            "9159971234", "Toyota", "Camry", "FQZ-123");
    ArrayList<Golfer> golfers = new ArrayList<>();

    @Mock
    GolferDAO dao;

    @Rule
    public MockitoRule mockitoRule_GDAO = MockitoJUnit.rule();

    @Before
    public void init() {
        app = new App();
        gService = new GolferService(dao);
        golfers.add(golfer);
    }

    /* ==================    GOLFER TESTS    =================== */

    @Test
    public void getSingleGolfer() {
        Golfer temp = new Golfer(1L, "rudy", "1234 memory lane", "6166179939",
                "6166179876", "Ford", "Bronco", "FTW-680");
//        try {
//            Mockito.when(dao.viewGolferInfo(temp)).thenReturn(golfers);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        golfers = gService.viewGolfer(golfer);
        for(Golfer e : golfers)
            System.out.println(e.toString());;
//        Assert.assertNotNull("Didnt return anyone", temp);

    }


}

