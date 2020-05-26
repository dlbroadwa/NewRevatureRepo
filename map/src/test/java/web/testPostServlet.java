package web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class testPostServlet {




    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;


    @Before
   public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() throws Exception {

        Gson gsonIn = new Gson();
        String jsonS = "";
        BufferedReader in = request.getReader();
        String inputLine;
        Writer out = response.getWriter();

        response.setCharacterEncoding("UTF-8");

        JsonObject jsonObject = gsonIn.fromJson(jsonS, JsonObject.class);
        when(request.getParameter("city")).thenReturn("city");
        when(request.getParameter("state")).thenReturn("state");




        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        PostServlet mock = mock(PostServlet.class);
        PostServlet web = new PostServlet();
        PostServlet spy = spy(web);
        //new PostServlet ().doPost(request, response);
//        web.doPost(request,response);
//        Mockito.verify(mock,Mockito.times(1)).doGet(request,response);

    }
}
