package com.Project0.market;

//insert into project0.trades
//        (brokerid, companyid , occurance, stockprice, numberofstocks)
//        values (625840, 310765, now(), 145,300)
//        ;

import com.Project0.Repository;
import com.Project0.companies.StockCompanies;
import com.Project0.companies.StockCompaniesRepository;
import com.Project0.stocks.StockRepository;
import com.Project0.stocks.Stocks;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TradesRepository implements Repository<MarketTrades, Integer>
{
    private PostgresConnectionUtilities connectionUtilities;

    public TradesRepository(PostgresConnectionUtilities connection)
    {
        if (connection != null)
        {
            this.connectionUtilities = connection;
        }
    }
    @Override
    public MarketTrades findbyID(Integer id) {
        Connection connection = null;
        ArrayList stocks = new ArrayList(0);
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from project0.trades" +
                    " where tradenum = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            if (results.next())
            {
                String tradeno = results.getString("tradenum");
                String brokerid = results.getString("brokerid");
                String coid = results.getString("companyid");
                String total_shares = results.getString("numberofstocks");
                String value = results.getString("stockprice");
                String occurence = results.getString("occurance");
                return(new MarketTrades(tradeno,brokerid,coid, total_shares, value ,occurence));
            }
        } catch (PSQLException exception) {
            //System.out.println(id + " Could not be added to the Database");
            exception.printStackTrace();
        } catch (SQLException exception) {
            //System.out.println(id + " Could not be added to the Database");
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException except) {
                    except.printStackTrace();
                }

            }
        }
        return null;
    }
    @Override
    public List<MarketTrades> findAll()
    {
        Connection connection = null;
        ArrayList trades = new ArrayList(0);
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from " + schema + ".trades";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            while  (results.next())
            {
                String tradeno = results.getString("tradenum");
                String brokerid = results.getString("brokerid");
                String coid = results.getString("companyid");
                String total_shares = results.getString("numberofstocks");
                String value = results.getString("stockprice");
                String occurence = results.getString("occurance");
                trades.add(new MarketTrades(tradeno,brokerid,coid, total_shares, value ,occurence));
            }
            return trades;
        } catch (PSQLException exception) {
            exception.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException except) {
                    except.printStackTrace();
                }

            }
        } return null;
    }
    @Override
    public Integer save(MarketTrades mt)
    {
        update(mt, mt.getTradeNum());
        System.out.println("Trade numbered " + mt.getCompanyID() + " added to Database");
        return mt.getCompanyID();
    }

    @Override
    public void update(MarketTrades trade, Integer id)
    {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "insert into "+schema +".trades "
             + "(brokerid, companyid,occurance,stockprice,numberofstocks ) "
             +"values ("+trade.getBrokerID()+","+trade.getCompanyID()+", now(),"
             +trade.getValueOfStock() +","+trade.getNumStocks()+")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
            Stocks sc = new StockRepository(connectionUtilities).findbyID(trade.getCompanyID());
            preparation = "Update "+schema+".stocks set tradingshares ="+
            (sc.getTradeableShares()-trade.getNumStocks()) +" where (companyid = "
            +"'"+sc.getCompanyID()+"')" ;
            statement.executeUpdate(preparation);

        }
        catch (PSQLException exception)
        {
            exception.printStackTrace();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException except)
                {
                    except.printStackTrace();
                }

            }
        }
    }


    public void delete(Integer tradenum)
    {
        Connection connection = null;

        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "delete from " + schema + ".trades"+
                    "where tradenum = '"+tradenum+'\'';
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException except) {
                    except.printStackTrace();
                }

            }
        }
    }
    public static void printTrades() {
        Connection connection = null;

        try {
            PostgresConnectionUtilities connectionUtilities = new PostgresConnectionUtilities();
            connection = connectionUtilities.getConnection();
            String preparation = "select t.tradenum,t.occurance,c.name Company," +
                    "sb.firstname firstname,sb.lastname lastname, t.occurance Date" +
                    " from project0.trades as t " +
                    "join project0.stockbrokers as sb on sb.brokerid = t.brokerid " +
                    "join project0.stockcompany as c on c.id = t.companyid";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            System.out.println("|Trade #    |    Time    |      Company Name   |     Broker's Name   |     Date   |");
            while(results.next())
            {
                System.out.print( "|  "+results.getString(1)+"        |   ");
                System.out.print( results.getString(2)+"|   ");
                System.out.print( results.getString(3)+"   |   ");
                System.out.print( results.getString(4)+"   ");
                System.out.print( results.getString(5)+"  |    ");
                System.out.println( results.getString(6)+" |   ");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException except) {
                    except.printStackTrace();
                }
            }
        }
    }
}
