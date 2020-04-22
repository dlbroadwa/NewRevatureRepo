package com.Project0.brokers;
//***************************************************************************//
import java.util.HashMap;
import java.util.Set;
//**************************************************************************//


public class StockBrokers //Class representing the actual Stock Brokers in the database.
{
    private static HashMap<Integer,StockBrokers> allBrokers = new HashMap<Integer,StockBrokers>();
    private static int n = 0;
    private String firstName;
    private String lastName;
    private Integer brokerID;
    //**************************************************************************//
    //Creates a new stock broker using full name and as assignable ID, this should ONLY be called from the Repository
    // unless there is direct need to test this method.
    //**************************************************************************//
    public StockBrokers(String[] arguments)
    {
        this.firstName = arguments[0];
        this.lastName = arguments[1];
        try
        {
            this.brokerID = new Integer(arguments[2]);
        }
        catch (Exception e)
        {
            System.out.println("Error in StockBroker()");
            this.brokerID = 0;
        }

        allBrokers.put(brokerID, this);

    }
    //Unused Method, furhter iterations made for it to be provided in the Repository, as we no longer keep a static hash map of Brokers here
    public static StockBrokers getBrokerByID(Integer id)
    {
        try
        {
            if ((allBrokers.keySet()).contains(id))
            {
                return allBrokers.get(id);
            }
        }
        catch (Exception exception)
        {
            System.out.println("Broker not in Database, Broker: " + id +" needs first and last naem added");
        }
        return null;
    }

    //Standard POJO Getter and Setter for all private items.
    //**************************************************************************//
    public Integer getBrokerID()
    {
        return this.brokerID;
    }

    public void setBrokerID(Integer brokerID)
    {
        this.brokerID = brokerID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    //**************************************************************************//

    //Hash map still created and maintained, but this function is trumped by the repository as well
    public static HashMap<Integer, StockBrokers> getAllBrokers()
    {
        return allBrokers;
    }

    //Out of version, replaced by getAll in BrokerRepo
    public static void printAllBrokers()
    {
        HashMap<Integer,StockBrokers> allDem = StockBrokers.getAllBrokers();;
        Set<Integer> a = allDem.keySet();
        for (Integer i: a)
        {
            System.out.println(i +":  " + (allDem.get(i).getFirstName()) +
                    (allDem.get(i).getLastName()));
        }
    }

    //As opposed to returning a String object, can be used to simply print a specific broker's name, once gotten
    public String printBrokerName(Integer id)
    {
        StockBrokers sb = (getAllBrokers()).get(id);
        return (sb.getFirstName() + " " + sb.getLastName());
    }

    //Used for intiial tests, now out of use due to Repository being more up to date with the DB than the HashMap, and refresh() being removed from this Class.
    public static String findBrokerByID(Integer id)
    {
        HashMap<Integer,StockBrokers> allDem = StockBrokers.getAllBrokers();;
        if (allDem.keySet().contains(id)) return (allDem.get(id).getFirstName() + " " + allDem.get(id).getLastName());
        else return "No Broker by That ID";
   }

   //override of toS tring, for easy display, but not entriely formatted for anything other than testing
    @Override
    public String toString() {
        return "Stock Broker {" +
                "firstName = " + this.firstName+
                ", lastName = "+ this.lastName +
                ", brokerID = "+ this.brokerID +
                '}';
    }
}