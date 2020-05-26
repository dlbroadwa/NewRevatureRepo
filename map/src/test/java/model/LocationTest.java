package model;

import model.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class LocationTest {


    Location location = mock(Location.class);

    String street = "1600 Pennsylvania Ave NW";
    String city = "Washington";
    String state = "DC";
    String zipCode ="20500";
    String name = "Damier Ray";
    private float lat = -2.3566F;
    private float lng = 80.456F;




    @InjectMocks Location mockLocation = mock(Location.class);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
    }

    @Test

    public void locationTest(){

        // Mockito.when(location.getLatitude()).thenReturn(anyFloat());

        Mockito.when(location.getLocationId()).thenReturn(5);
        Mockito.when(location.getStreetNumber()).thenReturn(street);
        Mockito.when(location.getCity()).thenReturn(city);
        Mockito.when(location.getState()).thenReturn(state);
        Mockito.when(location.getZipcode()).thenReturn(zipCode);
//
        Assert.assertEquals(street,location.getStreetNumber());
        Assert.assertEquals(city, location.getCity());
        Assert.assertEquals(state, location.getState());
        Assert.assertEquals(zipCode, location.getZipcode());
        Assert.assertEquals(5,location.getLocationId());
//        Assert.assertEquals("38.897957", location.getLatitude());

    }

    @Test

    public void testConstructor(){

        Comparable<Location> comparable = mock(Comparable.class);
Location locate ;

locate = Mockito.spy(new Location(name,street,"route",city,state,zipCode,"US",lat,lng));


        Mockito.when(locate.getCountry()).thenReturn("US");
        location.getLatitude();
      //  Assert.assertTrue(true);


        Assert.assertEquals("US",locate.getCountry());


        Mockito.verify(location,times(1)).getLatitude();


    }

}
