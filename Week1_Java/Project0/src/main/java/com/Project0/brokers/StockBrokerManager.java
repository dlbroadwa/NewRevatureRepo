package com.Project0.brokers;
//***************************************************************************//

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//**************************************************************************//



public class StockBrokerManager
{
     public static void getAllBrokers()
    {
        HashMap<Integer,StockBrokers> allDem = StockBrokers.getAllBrokers();;
        Set<Integer> a = allDem.keySet();
        for (Integer i: a)
        {
            System.out.println(i +":  " + (allDem.get(i).getFirstName()) +
                    (allDem.get(i).getLastName()));
        }
    }

    public static StockBrokers makeBroker(String[] arguments)
    {
        StockBrokers a = new StockBrokers(arguments);
        return a;
    }

    public static String printBrokerName(Integer id)
    {
        StockBrokers sb = (StockBrokers.getAllBrokers()).get(id);
        return (sb.getFirstName()+ " " + sb.getLastName());
    }

    public static String findBrokerByID(Integer id)
    {
        HashMap<Integer,StockBrokers> allDem = StockBrokers.getAllBrokers();;
        return (allDem.get(id).getFirstName() + " " + allDem.get(id).getLastName());
    }
}
