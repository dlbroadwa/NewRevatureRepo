package tests;
//*************************************************//
import com.Project0.brokers.StockBrokers;
import com.Project0.brokers.StockBrokerRepository;
import com.Project0.utilities.ConnectionUtilities;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
//***************************************************//

public class BrokersTests
{
    @Test
    public void deleteStockBrokerRepository()
    {
        StockBrokerRepository repo = new StockBrokerRepository(new PostgresConnectionUtilities());
        List<StockBrokers> sb = repo.findAll();
        for (StockBrokers temp : sb)
        {
            System.out.println("Deleted: " + temp.getBrokerID().toString()+", "+temp.getLastName());
            repo.delete(temp.getBrokerID());
        }

    }

    @Test
    public void testPrintBrokerName()
    {
        new StockBrokers (new String[]{"Sam", "Rockwell", "673251"});
        System.out.println(StockBrokers.printBrokerName(673251));
    }

    @Test
    public void testMakeBroker()
    {
        StockBrokerRepository repo = new StockBrokerRepository(new PostgresConnectionUtilities());
        repo.update((new StockBrokers (new String[]{"Jean", "Aldoph", "625840"})),625840);
        repo.update((new StockBrokers (new String[]{"Sam", "Rockwell", "673251"})),673251);
        repo.update((new StockBrokers (new String[]{"Tiffany", "Longstalking", "968510"})),968510);
        repo.update((new StockBrokers (new String[]{"Brain", "Till", "669817"})),669817);
        repo.update((new StockBrokers (new String[]{"Samantha", "Forchelli", "619851"})),619851);
        repo.update((new StockBrokers (new String[]{"Prudence", "Passion", "670369"})),670369);
    }

    @Test
    public void testGetBrokerByID()
    {
        System.out.println(StockBrokers.findBrokerByID(670369));
    }

    @Test
    public void getAllBrokers()
    {
        List all =  new StockBrokerRepository(new PostgresConnectionUtilities()).findAll();
        System.out.println(all.toString());
    }
}