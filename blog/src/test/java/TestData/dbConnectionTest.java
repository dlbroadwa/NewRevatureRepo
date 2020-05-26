package TestData;

import data.LocationRepository;
import model.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class dbConnectionTest {


@InjectMocks
LocationRepository dbConnection;


    @Mock
     private Connection mockConnection;
    @Mock
   private PreparedStatement prst;
    @Mock
    private Statement mockStatement;

    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dbtest() throws SQLException {

        Mockito.when(mockConnection.createStatement()).thenReturn(prst);
        Mockito.when(mockStatement.executeUpdate(Mockito.any())).thenReturn(1);

        PreparedStatement val = prst;
        Assert.assertNotEquals(val, 1);
  //      Mockito.verify(mockConnection.createStatement() , Mockito.times(1));
    }


    LocationRepository repo = mock(LocationRepository.class);
    @Test
    public void testQuery(){


        boolean checkQuery = repo.Query("* FROM Table");
        Assert.assertFalse(checkQuery);
        Mockito.verify(repo).Query("* FROM Table");


    }

Location location = mock(Location.class);

    @Test
    public void testfindById(){

  Mockito.when(repo.findById(1)).thenReturn(location);

  Assert.assertEquals(repo.findById(1), location);

    }


    @Test
    public void testfindAll(){

        Iterator<String> iterator = mock(Iterator.class);

        when(iterator.next()).thenReturn("Damier").thenReturn("Raymond");
        String result = "Damier " + "Raymond";

        Assert.assertEquals("Damier Raymond",result);

    }


    @Test
    public void testfindLocation(){

        Mockito.when(repo.findLocation("String","String","String","String", "String")).thenReturn(location);
        Assert.assertEquals(repo.findLocation
                ("String","String","String","String", "String"),
                location);
    }

    @Test
    public void testcreate(){

        Mockito.when(repo.create(location)).thenReturn(location);
        Assert.assertEquals(repo.create(location),location);
    }


    @Test
    public void testupdate(){

        Mockito.when(repo.update(location)).thenReturn(location);
        Assert.assertEquals(repo.update(location),location);
    }


    @Test
    public void testDelete(){


repo.delete(location);
 Mockito.verify(repo,times(1)).delete(location);
    }



    @Test
    public void testFinfByID(){

        when(repo.findById(1)).thenReturn(location);


    }



}
