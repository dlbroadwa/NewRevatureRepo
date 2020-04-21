package tests;
//location=jdbc:postgresql://revdemo.cmyaylobpmky.us-east-2.rds.amazonaws.com:5432/postgres;schema=project0;username=jean;password=okinawa31
import com.Project0.companies.StockCompanies;
//import org.junit.Assert;
import com.Project0.companies.StockCompaniesRepository;
import com.Project0.stocks.StockRepository;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class CompanyTests
{
    @Test
    public void setup() {
        StockRepository repo2 = new StockRepository(new PostgresConnectionUtilities());
        repo2.clearTable();
        StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
        List<StockCompanies> scr = repo.findAll();
        for (StockCompanies temp : scr) {
            repo.delete(temp.getId());
            System.out.println(temp);
        }
    }
    @Test
     public void testAA()
    {
        StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
        repo.update((new StockCompanies(new String[]{"Walmart", "310765"})),310765);
        repo.update((new StockCompanies(new String[] {"Home Depot", "320394"})),320394);
        repo.update((new StockCompanies(new String[] {"Disney", "545484"})),545484);
        repo.update((new StockCompanies(new String[] {"Hyundai of USA", "475640"})),4756430);
        repo.update((new StockCompanies(new String[] {"Ford Motor Company", "340904"})),340904);
        repo.update((new StockCompanies(new String[]{"Pigs Feet","403212"})),403212);
    }

    @Test
    public void testB_makeCompany()
    {
        new StockCompanies(new String[] {"Ny Times", "002321","true"});
    }
    @Test
    public void testB_getCompanyNameByID_getAllCompaniesMap()
    {
        StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
        List<StockCompanies> eyeDees = repo.findAll();
        for (StockCompanies i :eyeDees)
        {
            System.out.println(i.getName());
        }
    }
    @Test
    public void testD_findAll()
    {
        StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
        List<StockCompanies> sc = repo.findAll();
        for (StockCompanies sco:sc) System.out.println(sco.toString());
    }
    @Test
    public void testC_delete()
    {
        StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
        repo.delete(002321);
    }
    @Test
    public void testA_deleteFull()
    {
        StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
        List <StockCompanies> scr = repo.findAll();
        for (StockCompanies temp:scr)
        {
            repo.delete(temp.getId());
        }
    }
}

