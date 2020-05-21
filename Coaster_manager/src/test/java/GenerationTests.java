/**
 * Initialized by Jean Aldoph II, This testing set will be
 * used to MOCK generate employees for the Management Service of the
 * theme park. These tests will only determine the functionality
 * of the tests, they DO NOT need to be called during service.
 */

//

import com.google.gson.Gson;
import data.GenerationDAO;
import data.SQLDatabaseEmployees;
import models.Employee;
import org.junit.Test;
import utils.PostgresConnectionUtil;
import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenerationTests {
//
//    ScriptEngineManager manager = new ScriptEngineManager();
//    ScriptEngine engine = manager.getEngineByName("JavaScript");
//    // read script file
//    engine.eval(Files.newBufferedReader(Paths.get("/EmployeeGeneration.js"), StandardCharsets.UTF_8)));
//
//    Invocable inv = (Invocable) engine;
// //call function from script file
// inv.invokeFunction("yourFunction", "param");

    @Test
    public void testGen()
    {

        GenerationDAO genDao = new GenerationDAO();
        try
        {
            genDao.makeAday();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


//    Article Body
//
//    Before implementing a call, make sure you have the URL for the API call, a valid API Key for the environment you are connecting to,
//    and any required data elements. In this example, it is requesting for details of an order by the Transaction ID.
//
//        Import a HTTP Client.
//        Assign the URL and get params to the URL string.
//        Setup the http client.
//        Put it in GET mode.
//        Assign the URL and parameters.
//        Make the request.
//        Process the Response.
//        Close the connection.
//
//
    @Test
    public void testCall()
    {
        // Fake example transaction ID: 3YC00XQKNVMZ
        String url = "https://randomuser.me/api/";

        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();

        // Create a method instance.
        GetMethod method = new GetMethod(url);

        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try
        {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK)
            {
                System.err.println("Method failed: " + method.getStatusLine());
            }


            // Read the response body.
            byte[] responseBody = method.getResponseBody();
            String rezzy = new String(responseBody);
            String[] stack = rezzy.split("\".\"");
            int holder = 1;
            String item = "";
            String item1 = "";
            String item2 = "";
            for (String i : stack)
            {
                if (i.trim().equals("first"))
                {
                    item = (stack[holder]).toString();
                }
                else if (i.trim().contains("email"))
                {
                    item1 =(stack[holder]).toString();
                }
                else if (i.trim().contains("last"))
                {
                    item2 = (stack[holder]).split("\"")[0];
                }
                //System.out.println(holder-1 +"   "+i);
                holder++;

            }
            if (item2 == "") item2 ="Smith";
            if (item1 == "") item1 = "John";
            System.out.println("first Name: " + item);
            System.out.println("Last Name:  "+item2);
            System.out.println("email:  "+item1);
            

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
    }

    @Test
    public void makeCustomers()
    {
        int i = 0;
        while (i++ < 500) new GenerationDAO().makeCustomer();
    }
}

