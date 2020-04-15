package com.Project0.brokers;
//***************************************************************************//
import java.util.HashMap;

//**************************************************************************//


public class StockBrokers
{
    static HashMap<Integer,StockBrokers> allBrokers = new HashMap<Integer,StockBrokers>();
    static int n = 0;
    private String firstName;
    private String lastName;
    private Integer brokerID;

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
            e.printStackTrace();
            System.out.println("ID defaulted to next 0");
            this.brokerID = 0;
        }

        allBrokers.put(brokerID, this);
    }

    public static StockBrokers getBrokerByID(Integer id)
    {
        return allBrokers.get(id);
    }

    protected Integer getBrokerID(StockBrokers a)
    {
        return a.brokerID;
    }

    protected void setBrokerID(Integer brokerID)
    {
        this.brokerID = brokerID;
    }

    protected String getFirstName()
    {
        return firstName;
    }

    protected void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    protected String getLastName()
    {
        return lastName;
    }

    protected void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public static HashMap<Integer, StockBrokers> getAllBrokers()
    {
        return allBrokers;
    }
}