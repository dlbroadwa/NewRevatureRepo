package com.Project0.brokers;
//***************************************************************************//
import com.Project0.utilities.ConnectionUtilities;
import com.Project0.utilities.PostgresConnectionUtilities;

import java.util.HashMap;
import java.util.Set;
//**************************************************************************//


public class StockBrokers
{
    private static HashMap<Integer,StockBrokers> allBrokers = new HashMap<Integer,StockBrokers>();
    private static int n = 0;
    private String firstName;
    private String lastName;
    private Integer brokerID;

    public StockBrokers()
    {
    }

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
            //e.printStackTrace();
            System.out.println("ID defaulted to 0");
            this.brokerID = 0;
        }

        allBrokers.put(brokerID, this);
        //(new StockBrokerRepository(new PostgresConnectionUtilities())).update(this, this.brokerID);
    }

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

    public static HashMap<Integer, StockBrokers> getAllBrokers()
    {
        return allBrokers;
    }
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

    public static String printBrokerName(Integer id)
    {
        StockBrokers sb = (getAllBrokers()).get(id);
        return (sb.getFirstName() + " " + sb.getLastName());
    }

    public static String findBrokerByID(Integer id)
    {
        HashMap<Integer,StockBrokers> allDem = StockBrokers.getAllBrokers();;
        if (allDem.keySet().contains(id)) return (allDem.get(id).getFirstName() + " " + allDem.get(id).getLastName());
        else return "No Broker by That ID";
   }

    @Override
    public String toString() {
        return "Stock Broker {" +
                "firstName = " + this.firstName+
                ", lastName = "+ this.lastName +
                ", brokerID = "+ this.brokerID +
                '}';
    }
}