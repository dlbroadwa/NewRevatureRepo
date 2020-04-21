package tests;
//location=jdbc:postgresql://revdemo.cmyaylobpmky.us-east-2.rds.amazonaws.com:5432/postgres;schema=project0;username=jean;password=okinawa31
import com.Project0.companies.StockCompanies;
import com.Project0.companies.StockCompaniesRepository;
import com.Project0.stocks.StockRepository;
import com.Project0.stocks.Stocks;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StocksTests
{

    @Test
    public void testZ_setup()
    {
        StockRepository repo = new StockRepository(new PostgresConnectionUtilities());
        repo.clearTable();
        repo.update((new Stocks(new String[]{"310765","7000000","7000000","145.00"})),310765);
        repo.update((new Stocks(new String[] {"320394","2500000","2500000","85"})),320394);
        repo.update((new Stocks(new String[] {"545484","9999999","100","795"})),545484);
        repo.update((new Stocks(new String[] {"4756430","250000","250000","26"})),4756430);
        repo.update((new Stocks(new String[] {"340904","150000","150000","5"})),340904);
    }
    @Test
    public void findByID()
    {
        StockRepository repo = new StockRepository(new PostgresConnectionUtilities());
        System.out.println(repo.findbyID(310765));
    }
    @Test
    public void testingSave()
    {
        StockRepository repo = new StockRepository(new PostgresConnectionUtilities());
        //StockCompaniesRepository repo2 = new StockCompaniesRepository(new PostgresConnectionUtilities());
        //repo2.update((new StockCompanies(new String[]{"403212","Pig's Feet","true"})),403212);
        repo.save(new Stocks(new String[]{"403212","160000","97643","55"}));

    }

    @Test
    public void printAllStock_Wcompanies()
    {
        StockRepository.printAllStocks(5);
    }
}
