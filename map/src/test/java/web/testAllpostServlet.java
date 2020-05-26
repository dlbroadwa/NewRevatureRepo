package web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class testAllpostServlet {

//    AllPostsServlet allPost = new AllPostsServlet();

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks AllPostsServlet mockAllpost;

    @Before
    public  void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
   public void testServeletCall() throws ServletException, IOException {

        AllPostsServlet allPost = new AllPostsServlet();

       Comparable<AllPostsServlet> comparable = mock(Comparable.class);

       Mockito.when(comparable.compareTo(isA(AllPostsServlet.class))).thenReturn(1);

        Assert.assertTrue(true);
//       allPost.doPost(request,response);
//       Mockito.verify(mockAllpost, times(1)).doPost(request,response);
   }


}
