package data;

import models.Attraction;
import utils.PostgresConnectionUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class GenerationDAO {

    public GenerationDAO()
    {
    }


    public void makeAday() throws IOException {
        FileReader in = null;
        Integer oldTickets = 0;
        try
        {

            in = new FileReader("resources/ticketsSoldLAst.txt");
            System.out.println(in.read());
            oldTickets = new Integer(in.read());

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
        SQLDatabaseExtAttractions extAttractionDB = new  SQLDatabaseExtAttractions(new PostgresConnectionUtil());
        Integer iterationBound = 0;
        while(base++ < iterationBound)
        {
            move = rand.nextInt(10_000);
            if((move < 10_000) & (move > 6_333))
            {
                continue;
            }
            else if ((move < 6_333)& (move > 3_333)) //New Customer buys 1-9 tickets
            {
                ticketsNow = rand.nextInt(9);
                //Manager findall Customers to make sure new customer isn't in DB
                //send message to customerTickets to make customer tickets
                //ticketsNow # of tickets for Tickets sent for customer
                //
            }
            else if (move < 3_333)
            {
                ticketsNow = rand.nextInt(9);
                //Manager findbyID Customers to make sure new customer is in DB
                //Send message to Coaster customer to add these tickets for new customer
                //ticketsSold += ticketNow # of tickets for Tickets sent for customer
                //
            }
        }
        //read from file how many tickets were made last time
        //oldTickets = new Integer(fileio)
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
            attractions = extAttractionDB.findAll();
            Iterator it = attractions.iterator();
            while (it.hasNext())
            {
                temp = (Attraction) it.next();
                if (temp.getRating()==ticketRating)
                {
                    System.out.println(temp);
                }
            }
            }


    }
}
