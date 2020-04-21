package tests;

import com.Project0.brokers.StockBrokerRepository;
import com.Project0.brokers.StockBrokers;
import com.Project0.companies.StockCompanies;
import com.Project0.companies.StockCompaniesRepository;
import com.Project0.market.MarketTrades;
import com.Project0.market.TradesRepository;
import com.Project0.stocks.StockRepository;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TradeTests
{
    @Test
    public void findbyID()
        {
            TradesRepository repo = new TradesRepository(new PostgresConnectionUtilities());
            MarketTrades trades = repo.findbyID(1);
            System.out.println(trades);
        }
     @Test
     public void findAll()
     {
         TradesRepository repo = new TradesRepository(new PostgresConnectionUtilities());
         List<MarketTrades> alpha = repo.findAll();
         for (MarketTrades mt : alpha)
         {
             System.out.println(mt);
         }
     }
     @Test
     public void makeTrade()
     {
        TradesRepository repo = new TradesRepository(new PostgresConnectionUtilities());
        repo.update(new MarketTrades("625840","310765","700"),625480);
         //System.out.println(new MarketTrades("2","625480","310765","700","145","11-14-2018"));
     }
     @Test
    public void deleteAll()
     {
         TradesRepository repo = new TradesRepository(new PostgresConnectionUtilities());
         List<MarketTrades> alpha = repo.findAll();
         for (MarketTrades mt : alpha)
         {
             repo.delete(mt.getTradeNum());
         }
     }

     @Test
    public void fullWipe()
     {
         TradesRepository repo0 = new TradesRepository(new PostgresConnectionUtilities());
         List<MarketTrades> alpha = repo0.findAll();
         for (MarketTrades mt : alpha)
         {
             repo0.delete(mt.getTradeNum());
         }
         StockRepository repo1 = new StockRepository(new PostgresConnectionUtilities());
         repo1.clearTable();

         StockCompaniesRepository repo2 = new StockCompaniesRepository(new PostgresConnectionUtilities());
         List <StockCompanies> scr = repo2.findAll();
         for (StockCompanies temp:scr)
         {
             repo2.delete(temp.getId());
         }
         StockBrokerRepository repo3= new StockBrokerRepository(new PostgresConnectionUtilities());
         List<StockBrokers> sb = repo3.findAll();
         for (StockBrokers temp : sb)
         {
             repo3.delete(temp.getBrokerID());
         }
     }
     @Test
    public void testAllPrinter()
     {
         TradesRepository.printTrades();
     }

}
