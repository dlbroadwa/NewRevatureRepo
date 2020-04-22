package com.Project0.brokers;
//**************************************************************************//
import com.Project0.Repository;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.postgresql.util.PSQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//**************************************************************************//

public class StockBrokerRepository implements Repository<StockBrokers, Integer> {
    private PostgresConnectionUtilities connectionUtilities;//Use this for any non-static connection coming through this Class.

    //Standard Constructor to instantiate connectionUtilities.
    //**************************************************************************//
    public StockBrokerRepository(PostgresConnectionUtilities connection) {
        if (connection != null) {
            this.connectionUtilities = connection;
        }
    }
    //**************************************************************************//

    //Newer version of SB.findByID, allows for better DB access than previous iterations, as everything comes
    // through this repo, it is more accurate.
    @Override
    public StockBrokers findbyID(Integer id)
    {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from " + schema + ".stockbrokers" +
                    " where (brokerid = " + id + ")";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            if (results.next()) {
                String bid = results.getString("brokerid");
                String fname = results.getString("firstname");
                String lname = results.getString("lastname");
                return (new StockBrokers(new String[]{bid, fname, lname}));
            }
        }//After Query ensure all of the connection details were settles.
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {

            if (connection != null) {
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
        return null;
    }

    // Find All, returns a List of brokers which are currently in the database, a good practice is to refresh
    // every few rounds in the application screens.
    @Override
    public List<StockBrokers> findAll() {
        Connection connection = null;
        ArrayList stockBrokers = new ArrayList(0);
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "Select * from " + schema + ".stockbrokers";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            //Basic Query, then selection from it.
            while (results.next()) {
                String id = results.getString("brokerid");
                String firstname = results.getString("firstname");
                String lastname = results.getString("lastname");
                StockBrokers shorterm = new StockBrokers(new String[]{firstname, lastname, id});
                stockBrokers.add(shorterm);
            }
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
        return stockBrokers;
    }

    //An update call with a return of the new broker ID, to verify it was created.
    @Override
    public Integer save(StockBrokers var1)
    {
        update(var1, var1.getBrokerID());
        return var1.getBrokerID();
    }


    //The meat and potatoes of the whole app, an Update statement w/ the Broker object passed from any other class.
    @Override
    public void update(StockBrokers sb, Integer id)
    {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "insert into " + schema +
                    ".stockbrokers(brokerid,firstname,lastname) values (" +
                    id+",'"+sb.getFirstName()+"','"+sb.getLastName()+"')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(preparation);
        } catch (PSQLException exception) {
            System.out.println("Broker already exists in DataBase");
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


    //Remove a broker from the DB and repo, should only be allowed from an admin login.
    @Override
    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = this.connectionUtilities.getConnection();
            String schema = this.connectionUtilities.getSchema();
            String preparation = "delete from " + schema + ".stockbrokers where brokerid = " + '\''+id+'\'';
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
}