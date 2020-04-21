package com.Project0.market;


//***************************************************************************//
import com.Project0.brokers.StockBrokerRepository;
import com.Project0.brokers.StockBrokers;
import com.Project0.companies.StockCompanies;
import com.Project0.companies.StockCompaniesRepository;
import com.Project0.stocks.StockRepository;
import com.Project0.stocks.Stocks;
import com.Project0.utilities.PostgresConnectionUtilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//**************************************************************************//


public class MarketTrades
{
    private Integer tradeNum;
    private Integer brokerID;
    private Integer companyID;
    private Integer numStocks;
    private Float valueOfStock;
    private String time;

    //For shaking init purposes
    public MarketTrades ()
    {

    }

    //for creating new trades to go into the data base.
    public MarketTrades(String brokerID, String companyID,
                        String numStocks) {
        StockRepository coRepo = new StockRepository(new PostgresConnectionUtilities());
        Stocks st = coRepo.findbyID(new Integer(companyID));
        Integer currentStock = st.getTradeableShares();
        if (currentStock - (new Integer(numStocks)) < 1)
        {
            System.out.println("Invalid trade, not enough available shares");
        }
        else
            {
            //Calculate price, time and trade id from queries
            //Also, remove bad information by automatically adjusting other elements,
            this.brokerID = new Integer(brokerID);
            this.companyID = new Integer(companyID);
            this.numStocks = new Integer(numStocks);
            this.valueOfStock = st.getCost();
            Connection connection;
            ArrayList trades = new ArrayList(0);
            try {
                connection = new PostgresConnectionUtilities().getConnection();
                String schema = connection.getSchema();
                System.out.println(schema);
                String preparation = "Select * from project0.trades";
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(preparation);
                results.next();
                connection = new PostgresConnectionUtilities().getConnection();
                preparation = "Select now from now()";
                statement = connection.createStatement();
                results = statement.executeQuery(preparation);
                results.next();
                this.time = results.getString("now");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //for puling trades from database
    public MarketTrades(String tradeNum, String brokerID, String companyID,
                        String numStocks, String valueOfStock, String date)
    {
        this.tradeNum = new Integer(tradeNum);
        this.brokerID = new Integer(brokerID);
        this.companyID = new Integer(companyID);
        this.numStocks = new Integer(numStocks);
        this.valueOfStock = new Float(valueOfStock);
        this.time = date;
    }

    public Integer getTradeNum() {
        return this.tradeNum;
    }

    public void setTradeNum(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Integer getBrokerID() {
        return this.brokerID;
    }

    public void setBrokerID(Integer brokerID) {
        this.brokerID = brokerID;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getNumStocks() {
        return numStocks;
    }

    public void setNumStocks(Integer numStocks) {
        this.numStocks = numStocks;
    }

    public Float getValueOfStock() {
        return valueOfStock;
    }

    public void setValueOfStock(Float valueOfStock) {
        this.valueOfStock = valueOfStock;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "tradeNum = " + tradeNum +
                ": broker's ID = " + brokerID +
                ", companies ID = " + companyID +
                ", Number of Stocks = " + numStocks +
                ", Value Of 1 Stock = " + valueOfStock +
                " made on:  " + time + "\n"+
                "Total sale price: $" + (this.getTotal()); }

    private Float getTotal()
    {
        return this.numStocks * this.valueOfStock;
    }
}


