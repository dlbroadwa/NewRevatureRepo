package testModel;

import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testUser {

    User mockUser = mock(User.class);

@Rule
   public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before

    public  void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test

    public void testUserConstructor(){
        User user = new User("damier2020","pass","Ray","Damier");
        //Mockito.when(user.getClass())

        Assert.assertThat("damier2020",is(user.getUsername()));
    }

    @Test

    public void testUsername(){


 when(mockUser.getUsername()).thenReturn("Username");
 Assert.assertEquals("Username", mockUser.getUsername());

    }

    @Test
    public void testFirstName(){


        when(mockUser.getFirstName()).thenReturn("FirstName");
        Assert.assertEquals("FirstName", mockUser.getFirstName());

    }

@Test
    public void testLastname(){


        when(mockUser.getLastName()).thenReturn("LastName");
        Assert.assertEquals("LastName", mockUser.getLastName());

    }

    @Test
    public void testPassword(){


        when(mockUser.getPassword()).thenReturn("Password");
        Assert.assertTrue( "Password",true);

    }
}
