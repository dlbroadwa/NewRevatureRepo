package data;


import models.Attraction;
import models.Customer;
import models.Employee;
import redis.clients.jedis.Jedis;
import utils.PostgresConnectionUtil;
import java.io.*;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.lang.reflect.Array;
import java.util.*;


public class GenerationDAO {

    public GenerationDAO()
    {
    }


    public ArrayList makeAday() throws IOException {
        FileReader in = new FileReader("resources\\ticketsSoldLAst.txt");
        ArrayList response= new ArrayList();
        BufferedReader br = new BufferedReader(in);
        Integer oldTickets = 0;
        //read from file how many tickets were made last time

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
        List<String> newEmails = new ArrayList();
        base = 0;
        List<Attraction> attractions = new ArrayList();
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
        List<Employee> elist = employeeDB.findAll();
        Integer iterationBound = 500;
        while(base++ < iterationBound)
        {
            //System.out.println(base);
            move = rand.nextInt(10_000);
            if((move <= 10_000) & (move > 6_333))
            {
                continue;
            }
            else if ((move <= 6_333)& (move > 3_333)) //Old Customer buys 1-9 tickets
            {
                ticketsNow = rand.nextInt(10);
                //select random customer from list
                customer = list.get(rand.nextInt(list.size()));
                //send message to customerTickets to make customer tickets.;
                //Messaging goes here
                sendMessage(customer, ticketsNow);

                ticketsSold += ticketsNow;
            }
            else if (move <= 3_333) //New Customer come to park
            {
                ticketsNow = rand.nextInt(10);
                //Manager findbyID Customers to make sure new customer is not in DB
                Customer temporaryCustomer = makeCustomer();
                newEmails.add(temporaryCustomer.getEmail());
                //send message to customerTickets to make customer tickets.;


                //Messaging goes here
                sendMessage(temporaryCustomer, ticketsNow);

                ticketsSold += ticketsNow; //# of tickets for Tickets sent for customer

            }
        }


        ticketDiff = oldTickets - ticketsSold;
        if ((ticketDiff > 89 ) || (ticketDiff<-89)) ticketRating = 10;
        else if ((ticketDiff > 69 ) || (ticketDiff<-69)) ticketRating = 9;
        else if ((ticketDiff > 49 ) || (ticketDiff<-49)) ticketRating = 8;
        else if ((ticketDiff > 39 ) || (ticketDiff<-39)) ticketRating = 7;
        else if ((ticketDiff > 29 ) || (ticketDiff<-29)) ticketRating = 6;
        else if ((ticketDiff > 19 ) || (ticketDiff<-19)) ticketRating = 5;
        else ticketRating = 4;

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
                    extAttractionDB.add(temp);
                    break;
                }
            }
            int iters = 0;
            //Add # of employees == ticket rating
            while (iters < ticketRating)
            {
                employeeDB.remove(elist.get(rand.nextInt()).getId());
                iters++;
            }
        }
        else if(ticketDiff < 0)
        {
            int wait = 0;
            attractions = intAttractionDB.findAll();
            Iterator it = attractions.iterator();
            while (it.hasNext())
            {
                temp = (Attraction) it.next();
                if (temp.getRating()==ticketRating)
                {
                    intAttractionDB.remove(temp.getId());
                    break;
                }
            }
            //Remove # of employees = to ticketRating
            int iters = 0;
            while (iters < ticketRating)
            {
                employeeDB.add(makeEmployee());
                iters++;
            }
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
            //Return a Breakdown of what happened in the Generation for write-back to
            //Servlet
        response.add(newEmails.size());

        return response;

        //Response:
        // [0]: # new Customers
        // [1]: # return customers,
        // [2]: new Attraction ID / Removed attraction ID
        // [3]: # of employees hired or fired
        // [4]: ticket Diff
    }

    public Customer makeCustomer()
    {
        String startUp = null;
        while (startUp == null)
        {
            startUp = indicativeFoul();
        }
        String[] values =  startUp.split("!");
        Customer newCustomer = new Customer(values[0],values[1],values[2],"password");
        SQLDatabaseCustomerDAO customerDB = new SQLDatabaseCustomerDAO(new PostgresConnectionUtil());
        customerDB.save(newCustomer);
        Customer returnValue = customerDB.findById(values[2]);
        List<Customer> cml = customerDB.findAll();
        if (returnValue != null)
        {
            return returnValue;
        }
        else
        {
            return cml.get(new Random().nextInt(cml.size()));
        }


    }

    public Employee makeEmployee()
    {
        String startUp = null;
        while (startUp == null)
        {
                startUp = indicativeFoul();
        }
        String[] values =  startUp.split("!");
        Employee newEmployee = new Employee(values[0],values[1],"9999999999",values[2],"password",12,false);
        SQLDatabaseEmployees employeeDB = new SQLDatabaseEmployees(new PostgresConnectionUtil());
        if (employeeDB.add(newEmployee))
        {
            Employee returnValue = employeeDB.findByID(newEmployee.getId());

            return returnValue;
        }
        else
        {
            List<Employee> eml = employeeDB.findAll();
            return eml.get(new Random().nextInt(eml.size()));
        }
    }

    public String indicativeFoul()
    {
        String url = "https://randomuser.me/api/";
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        String item = "";
        String item1 = "";
        String item2 = "";
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            byte[] responseBody = method.getResponseBody();
            String rezzy = new String(responseBody);
            String[] stack = rezzy.split("\".\"");
            int holder = 1;
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
            if ((item.trim() == null) || (item2.trim() == null) || (item1.trim() ==null)) return null;

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
        if ((item.trim() == "") || (item2.trim() == "") || (item1.trim() == "")) return null;
        return item.trim()+"!"+item2.trim()+"!"+item1.trim();
    }

    public void sendMessage(Customer c, int i)
    {

        Jedis jedis = new Jedis();
        jedis.sadd(c.toString(),String.valueOf(i));

    }

}
