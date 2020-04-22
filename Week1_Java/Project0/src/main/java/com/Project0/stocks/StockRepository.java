package com.Project0.stocks;

//***************************************************************************//
import com.Project0.Repository;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//***************************************************************************//
//***************************************************************************//
// All Override Documentation found in Repositories Abstract Class.
//***************************************************************************//

public class StockRepository implements Repository<Stocks, Integer> {
    private PostgresConnectionUtilities connectionUtilities;

    public StockRepository(PostgresConnectionUtilities connection) {
        if (connection != null) {
            this.connectionUtilities = connection;
        }
    }

    @Override
    public Stocks findbyID(Integer id) {
        Connection connection = null;
        ArrayList stocks = new ArrayList(0);
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from " + schema + ".Stocks" +
                    " where (companyid = " + id + ")";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            if (results.next())
            {
                String coid = results.getString("companyid");
                String total_shares = results.getString("totalshares");
                String up_shares = results.getString("tradingshares");
                String value = results.getString("sharevalue");
                return(new Stocks(new String[]{coid, total_shares, up_shares, value}));
            }
        } catch (PSQLException exception) {
            System.out.println(id + " Could not be added to the Database");
            //exception.printStackTrace();
        } catch (SQLException exception) {
            System.out.println(id + " Could not be added to the Database");
            //exception.printStackTrace();
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

    @Override //Work this out
    public List<Stocks> findAll() {
        Connection connection = null;
        ArrayList stocks = new ArrayList(0);
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from " + schema + ".Stocks";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);

            while (results.next()) {
                String id = results.getString("companyid");
                String total_shares = results.getString("totalshares");
                String up_shares = results.getString("tradingshares");
                String value = results.getString("sharevalue");
                Stocks shorterm = new Stocks(new String[]{id, total_shares, up_shares, value});
                stocks.add(shorterm);
            }
            return stocks;
        } catch (PSQLException exception) {
            //exception.printStackTrace();
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
        return stocks;
    }

    @Override
    public Integer save(Stocks stock) {
        update(stock, stock.getCompanyID());
        System.out.println(stock.getCompanyID() + " added to Database");
        return stock.getCompanyID();
    }


    @Override
    public void update(Stocks st, Integer id) {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "insert into " + schema +
                    ".stocks(companyid,totalshares,tradingshares,sharevalue) values (" +
                    id + ",'" + st.getTotalShares() + "','" + st.getTradeableShares() + "', '" + st.getCost() + "' )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
            //System.out.println(preparation);
        } catch (PSQLException exception) {
            exception.printStackTrace();
            //System.out.println("Stock already exists in DataBase");
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Failed Connection");
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


    @Override
    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "delete from " + schema + ".Stocks where companyid = " + '\'' + id + '\'';
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
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
        }
    }

    //Used for testing when seeking to easily reset the table of Stocks
    //Good when you plan on running multiple tests and each one could easily crash the others.
    //Typically ran before a test and after a test, along with a "batch update".
    public void  clearTable()
    {        Connection connection = null;
        try
        {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "delete from " + schema + ".Stocks *";
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
        }
        catch (PSQLException exception)
        {
            exception.printStackTrace();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        } finally
        {
            if (connection != null) {
                try
                {
                    connection.close();
                }
                catch (SQLException except) {
                    except.printStackTrace();
                }
            }
        }
    }

    //Similar to printing all trades, but focuses on information a broker may be most interested in.
    //Shows companies and their stock information for quick access by a broker.
    //Can be used to track the most highly profitable of stocks available. If prices change.
    public static void printAllStocks(Integer sortby)
    {
        Connection connection = null;
        try {
            PostgresConnectionUtilities connectionUtilities = new PostgresConnectionUtilities();
            connection = connectionUtilities.getConnection();
            String preparation = "select s.companyid,c.name,s.totalshares,s.tradingshares,s.sharevalue "+
                    "from project0.stocks as s "+
                    "join project0.stockcompany c on s.companyid =c.id "+
                    "order by "+sortby+" desc";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            System.out.println("|  CompanyID   |   Company Name   |   Total Shares   |      Up Shares " +
                    "   |     Price Per Share   |");
            while (results.next())
            {
                System.out.print( "|  "+results.getString(1)+"        |   ");
                System.out.print( results.getString(2)+"    |   ");
                System.out.print( results.getString(3)+"   |   ");
                System.out.print( results.getString(4)+"  |   $");
                System.out.println( results.getString(5)+"  |    ");

            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        } finally
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
}