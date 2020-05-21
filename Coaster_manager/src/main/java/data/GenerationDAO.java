package data;

import com.google.gson.Gson;
import models.Attraction;
import models.Customer;
import models.Employee;
import utils.PostgresConnectionUtil;

import java.io.*;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.util.*;


public class GenerationDAO {

    public GenerationDAO()
    {
    }


    public void makeAday() throws IOException {
        FileReader in = new FileReader("resources\\ticketsSoldLAst.txt");
        BufferedReader br = new BufferedReader(in);
        Integer oldTickets = 0;
        try
        {

            oldTickets = new Integer(br.readLine());
            System.out.println(oldTickets);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null) in.close();

        }
        Random rand = new Random();
        Integer base, ticketsSold;
        Integer move;
        Integer ticketDiff;
        base = 0;
        List<Attraction> attractions = new ArrayList<Attraction>();
        Integer ticketsNow;
        Attraction temp;
        ticketsSold = 0;
        Integer ticketRating = 0;
        SQLDatabaseIntAttraction intAttractionDB = new  SQLDatabaseIntAttraction(new PostgresConnectionUtil());
        SQLDatabaseExtAttractions extAttractionDB = new  SQLDatabaseExtAttractions(new PostgresConnectionUtil());
        SQLDatabaseCustomerDAO customerDB = new SQLDatabaseCustomerDAO(new PostgresConnectionUtil());
        SQLDatabaseEmployees employeeDB = new SQLDatabaseEmployees(new PostgresConnectionUtil());
        Attraction attraction;
        Customer customer;
        Employee employee;
        List<Customer> list = customerDB.findAll();
        Integer iterationBound = 500;
        while(base++ < iterationBound)
        {
            //System.out.println(base);
            move = rand.nextInt(10_000);
            if((move < 10_000) & (move > 6_333))
            {
                continue;
            }
            else if ((move < 6_333)& (move > 3_333)) //Old Customer buys 1-9 tickets
            {
                ticketsNow = rand.nextInt(10);
                //select random sutomer form list
                customer = list.get(rand.nextInt(list.size()));
                //send message to customerTickets to make customer tickets.;
                ticketsSold += ticketsNow;
            }
            else if (move < 3_333)
            {
                ticketsNow = rand.nextInt(10);
                //Manager findbyID Customers to make sure new customer is in DB
                //Customer temporaryCustomer = makeCustomer();
                //customerDB.findById(temporaryCustomer.getCustomerID());
                //Send message to Coaster customer to add these tickets for new customer
                ticketsSold += ticketsNow; //# of tickets for Tickets sent for customer

            }
        }
        //read from file how many tickets were made last time

        ticketDiff = oldTickets - ticketsSold;
        if ((ticketDiff > 89 ) || (ticketDiff<-89)) ticketRating = 10;
        else if ((ticketDiff > 69 ) || (ticketDiff<-69)) ticketRating = 9;
        else if ((ticketDiff > 49 ) || (ticketDiff<-49)) ticketRating = 8;
        else if ((ticketDiff > 39 ) || (ticketDiff<-39)) ticketRating = 7;
        else if ((ticketDiff > 29 ) || (ticketDiff<-29)) ticketRating = 6;
        else if ((ticketDiff > 19 ) || (ticketDiff<-19)) ticketRating = 5;
        else ticketRating = 4;
        System.out.println(ticketRating);

        if(ticketDiff > 0)
        {
            int wait = 0;
            attractions = extAttractionDB.findAll();
            Iterator it = attractions.iterator();
            while (it.hasNext())
            {
                temp = (Attraction) it.next();
                if (temp.getRating()==ticketRating)
                {
                    //System.out.println(temp);

                    wait++;
                }
            }
            System.out.println(wait);
            }
            FileWriter win = new FileWriter("resources\\ticketsSoldLAst.txt");

            try
            {
                PrintWriter write = new PrintWriter(win);
                write.println(ticketsSold);
                write.close();
                System.out.println(ticketDiff);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (in != null) in.close();

            }
    }

//    private Customer makeCustomer() {
//        return new Customer();
//    }

    public Employee makeEmployee()
    {
        
    }

    public String indicativeFoul()
    {
        String url = "https://randomuser.me/api/";
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            byte[] responseBody = method.getResponseBody();
            System.out.println(new String(responseBody));
            responseBody.
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

}
