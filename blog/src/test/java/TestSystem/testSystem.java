package TestSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import system.PostContextListener;

import javax.servlet.ServletContextListener;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testSystem {


    ServletContextListener mockServeletContect =  mock(ServletContextListener.class);

    PostContextListener mockPostContext = mock(PostContextListener.class);


    @Before

    public  void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test

    public void testContextListernerClass(){

        Comparable<PostContextListener> compare = mock(Comparable.class);

        when(compare.compareTo(isA(PostContextListener.class))).thenReturn(0);

        Assert.assertEquals(0,compare.compareTo(new PostContextListener()));

    }

}
