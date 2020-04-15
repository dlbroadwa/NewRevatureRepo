package tests;

import com.Project0.brokers.StockBrokerManager;
import com.Project0.brokers.StockBrokers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;


public class BrokersTests
{
    @Before
    public void setupStockBrokerManager() {
        StockBrokerManager.makeBroker(new String[]{"Sam", "Rockwell", "673251"});
        StockBrokerManager.makeBroker(new String[]{"Tiffany", "Longstalking", "968510"});
        StockBrokerManager.makeBroker(new String[]{"Brain", "Till", "669817"});
        StockBrokerManager.makeBroker(new String[]{"Samantha", "Forchelli", "619851"});
        StockBrokerManager.makeBroker(new String[]{"Prudence", "Passion", "670369"});
    }

    @Test
    public void testPrintBrokerName()
    {
        StockBrokerManager.makeBroker(new String[]{"Sam", "Rockwell", "673251"});
        System.out.println(StockBrokerManager.printBrokerName(673251));
    }

    @Test
    public void testMakeBroker()
    {
        StockBrokerManager.makeBroker(new String[]{"Jean", "Aldoph", "625840"});
    }

    @Test
    public void testGetBrokerByID()
    {
        StockBrokerManager.findBrokerByID(670369);
    }

    @Test
    public void getAllBrokers()
    {
        getAllBrokers();
    }
}