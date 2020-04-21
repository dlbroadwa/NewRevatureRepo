package test.java;

import com.ex.DAO.*;
import com.ex.guests.GuestAccess;
import com.ex.main.InventoryScreen;
import com.ex.main.Runner;
import com.ex.main.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class GuestsTests {

    Screen guests, invo;
    Runner run,connectionUtil;
    GetEnvironmentVar getVar;


  @Mock
    SqlDatabaseAnimals animals;


    //@Rule

    @Before
    public void init() {
    guests = new GuestAccess();
    invo = new InventoryScreen(false);
    getVar = new GetEnvironmentVar();
    connectionUtil = new PostgresConnectionUtil();
    }

    @Test
    public void openGuestAccess(){
        guests.doScreen(run);
    }

    @Test
    public void testGetVar(){
        Assert.assertEquals("project_0", getVar.getSchema());
    }

//    @Test
//    public void testConnectUtil() throws SQLException {
//        Assert.assertNotNull(connectionUtil);
//    }
//
//    @BeforeClass
//    public void connectInit() throws SQLException {
//        connectionUtil = new PostgresConnectionUtil(getVar.getUrl(), getVar.getUsername(), getVar.getPassword(), getVar.getSchema());
//    }
//
//    @Test
//    public void testInvo(){
//       animalsDAO = new SqlDatabaseAnimals(connectionUtil);
//         List<Animals> allAnimals = animalsDAO.findAll();
//         Assert.assertNotNull(animals);
//    }

}

