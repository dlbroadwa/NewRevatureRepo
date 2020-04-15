package tests;

import com.Project0.companies.StockCompaniesManager;
import com.Project0.companies.StockCompanies;
//import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class CompanyTests
{
    @Before
    public void setup()
    {
        StockCompaniesManager.makeCompany(new String[]{"Walmart", "310765"});
        StockCompaniesManager.makeCompany(new String[] {"Home Depot", "320394"});
        StockCompaniesManager.makeCompany(new String[] {"Disney", "545484"});
        StockCompaniesManager.makeCompany(new String[] {"Hyundai of USA", "4756430"});
        StockCompaniesManager.makeCompany(new String[] {"Ford Motor Company", "340904"});
    }
    @Test
    public void makeCompany()
    {
        StockCompaniesManager.makeCompany(new String[] {"Ny Times", "002321"});
    }
    @Test
    public void getCompanyNameByID_getAllCompanies()
    {
        Set<Integer> eyeDees = StockCompaniesManager.getAllCompanies().keySet();
        for (Integer i :eyeDees)
        {
            System.out.println(StockCompaniesManager.getNameByID(i));
        }
    }

}

