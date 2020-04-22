package com.Project0.market;

//***************************************************************************//
import com.Project0.Repository;
import com.Project0.brokers.StockBrokerRepository;
import com.Project0.brokers.StockBrokers;
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
import java.util.Random;
//***************************************************************************//

public class TradesRepository implements Repository<MarketTrades, Integer> {
    private PostgresConnectionUtilities connectionUtilities;

    //Always pass a fresh, or still open connection.
    public TradesRepository(PostgresConnectionUtilities connection) {
        if (connection != null) {
            this.connectionUtilities = connection;
        }
    }

    //See explanation in any other repo, this is rather self explanatory. The biggest note being,
    //This repo has 3 table dependencies, stock, companies and brokers. therefore
    //must be much more inclusive.
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
            if (results.next()) {
                String tradeno = results.getString("tradenum");
                String brokerid = results.getString("brokerid");
                String coid = results.getString("companyid");
                String total_shares = results.getString("numberofstocks");
                String value = results.getString("stockprice");
                String occurence = results.getString("occurance");
                return (new MarketTrades(tradeno, brokerid, coid, total_shares, value, occurence));
            }
        } catch (PSQLException exception) {
            exception.printStackTrace();
            exception.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
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

    //See other Repos for explanation
    @Override
    public List<MarketTrades> findAll() {
        Connection connection = null;
        ArrayList trades = new ArrayList(0);
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from " + schema + ".trades";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            while (results.next()) {
                String tradeno = results.getString("tradenum");
                String brokerid = results.getString("brokerid");
                String coid = results.getString("companyid");
                String total_shares = results.getString("numberofstocks");
                String value = results.getString("stockprice");
                String occurence = results.getString("occurance");
                trades.add(new MarketTrades(tradeno, brokerid, coid, total_shares, value, occurence));
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
        }
        return null;
    }

    @Override
    public Integer save(MarketTrades mt) {
        update(mt, mt.getTradeNum());
        System.out.println("Trade numbered " + mt.getCompanyID() + " added to Database");
        return mt.getCompanyID();
    }

    @Override
    public void update(MarketTrades trade, Integer id) {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "insert into " + schema + ".trades "
                    + "(brokerid, companyid,occurance,stockprice,numberofstocks ) "
                    + "values (" + trade.getBrokerID() + "," + trade.getCompanyID() + ", now(),"
                    + trade.getValueOfStock() + "," + trade.getNumStocks() + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
            Stocks sc = new StockRepository(connectionUtilities).findbyID(trade.getCompanyID());
            preparation = "Update " + schema + ".stocks set tradingshares =" +
                    (sc.getTradeableShares() - trade.getNumStocks()) + " where (companyid = "
                    + "'" + sc.getCompanyID() + "')";
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


    public void delete(Integer tradenum) {
        Connection connection = null;

        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "delete from " + schema + ".trades" +
                    "where tradenum = '" + tradenum + '\'';
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

    //Unique to This section, this attempts a readable console display of all the trade made recently.
    //This may not be ideal, but ti provides a lot of information rather quickly compared to many other functions,
    //Returns nothing and makes only a single query, where most other functions wont have an extensive use, we can use
    //this to get a great overview of the static values in place within the database, and the trades made over the recent days.
    public static void printTrades() {
        Connection connection = null;

        try {
            PostgresConnectionUtilities connectionUtilities = new PostgresConnectionUtilities();
            connection = connectionUtilities.getConnection();
            String preparation = "select t.tradenum, c.name," +
                    "sb.firstname,sb.lastname, t.occurance Date" +
                    " from project0.trades as t " +
                    "join project0.stockbrokers as sb on sb.brokerid = t.brokerid " +
                    "join project0.stockcompany as c on c.id = t.companyid order by 5";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            System.out.println("|Trade #    | Company Name   |     Broker's Name   |     Date   |  ");
            while (results.next()) {
                System.out.print("|  " + results.getString(1) + "|   ");
                System.out.print(results.getString(2) + "|   ");
                System.out.print(results.getString(3) + "      ");
                System.out.print(results.getString(4) + "  | ");
                System.out.println(results.getString(5) + "  |    ");
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

    //The heart of the Stock market is trading, and this is a simple way to simulate those trades given psuedo random
    //generation. there is a 1/3 chance a trade will be made per Iters, a random company and broker are set, and
    // psuedo random # of stocks are traded.
    public void shakeTheMarket(Integer iters) {
        Random random = new Random();
        Connection connection = null;
        Integer ticks = 0;
        try {
            PostgresConnectionUtilities connectionUtilities = new PostgresConnectionUtilities();
            connection = connectionUtilities.getConnection();
            StockBrokerRepository sbrepo = new StockBrokerRepository(connectionUtilities);
            StockRepository strepo = new StockRepository(connectionUtilities);
            List<Stocks> lst = strepo.findAll();
            List<StockBrokers> lsb = sbrepo.findAll();
            while ((ticks++) < iters) {
                if (random.nextInt(3) != 1) {
                    StockBrokers sb = lsb.get(random.nextInt(lsb.size()));
                    Stocks st = lst.get(random.nextInt(lst.size()));
                    Integer date = random.nextInt(30);
                    this.update___But__For_Shakes(new MarketTrades(String.valueOf(sb.getBrokerID()), new String(String.valueOf(st.getCompanyID())), "5"), date);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This update is for the shakes only, and is used simply to adjust the time of trades, to simulate the last 30 days when called.
    private void update___But__For_Shakes(MarketTrades trade, Integer date) {
        {
            Connection connection = null;
            try {
                connection = this.connectionUtilities.getConnection();
                String schema = this.connectionUtilities.getSchema();
                String preparation = "insert into " + schema + ".trades "
                        + "(brokerid, companyid,occurance,stockprice,numberofstocks ) "
                        + "values (" + trade.getBrokerID() + "," + trade.getCompanyID() + ", now() - INTERVAL '" + date + " DAY', "
                        + trade.getValueOfStock() + "," + trade.getNumStocks() + ")";
                Statement statement = connection.createStatement();
                statement.executeUpdate(preparation);
                Stocks sc = new StockRepository(connectionUtilities).findbyID(trade.getCompanyID());
                preparation = "Update " + schema + ".stocks set tradingshares =" +
                        (sc.getTradeableShares() - trade.getNumStocks()) + " where (companyid = "
                        + "'" + sc.getCompanyID() + "')";
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
    }
}
