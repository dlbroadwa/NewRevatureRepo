package com.Project0.market;


//***************************************************************************//
import com.Project0.brokers.StockBrokers;
import com.Project0.companies.StockCompanies;
import com.Project0.stocks.Stocks;

//**************************************************************************//




public class StockMarket
{
    private class Market
    {
        StockBrokers[] brokers;
        StockCompanies[] companies;
        Stocks[] stocks;
        private Market Market(String[] brokerArguments, String[] companyArguments,
                              String[] stockArguments)
        {
            //Query Brokers, Companies and Stocks  for the lists of each set of args.
            //this build should have no functionality outside of collections.

            return this;
        }
    }

}
