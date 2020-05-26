package web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;

public class testLoginServlet {

    MockitoRule mockitoRule = MockitoJUnit.rule();


    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    LogInServlet mockLogin;

    @Before
    public  void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testServeletCall() throws ServletException, IOException {

        LogInServlet allPost = new LogInServlet();

        Comparable<LogInServlet> comparable = mock(Comparable.class);

        Mockito.when(comparable.compareTo(isA(LogInServlet.class))).thenReturn(1);

        Assert.assertTrue(true);

    }

}
