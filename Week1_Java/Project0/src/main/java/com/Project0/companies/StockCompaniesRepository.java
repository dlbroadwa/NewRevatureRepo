package com.Project0.companies;

//**************************************************************************//
import com.Project0.Repository;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.postgresql.util.PSQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//**************************************************************************//

//HEre is where Stock company magic occurs, using this class to continuously repopulate the repo.
public class StockCompaniesRepository implements Repository<StockCompanies, Integer>
{
    //With the other Repositories, this is a key component, everything else fails without it.
    private PostgresConnectionUtilities connection;

    //Standard Constructor. instantiates connection if it has been closed.
    public StockCompaniesRepository(PostgresConnectionUtilities connection)
    {
        if (connection!=null) this.connection = connection;
    }

    //Std repo method for Queries on the DB
    @Override
    public StockCompanies findbyID(Integer id) {
        Connection connection = null;
        ArrayList stockcompanies = new ArrayList(0);
        try {
            connection = this.connection.getConnection();
            String schema = this.connection.getSchema();
            String preparation = "Select * from " + schema + ".stockcompany" +
                    " where (id = " + id + ")";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);
            if (results.next()) {
                String coid = results.getString("id");
                String name = results.getString("name");
                System.out.println("Company: " +name);
                return (new StockCompanies(new String[]{name,coid}));
            }
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
                } catch (SQLException except) {
                    except.printStackTrace();
                }
            }
        }
        return null;
    }
    //Get the updated list of Companies from DB
    @Override
    public List<StockCompanies> findAll()
    {
        Connection connection = null;
        ArrayList stockComps = new ArrayList(0);
        try {
            connection = this.connection.getConnection();
            String schema = this.connection.getSchema();
            String preparation = "Select * from " + schema + ".stockcompany";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(preparation);

            while (results.next()) {
                String id = results.getString("id");
                String name = results.getString("name");
                StockCompanies shorterm = new StockCompanies(new String[]{name,id});
                stockComps.add(shorterm);
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
        return stockComps;
    }

    //Update w/ a print statement, limited usage
    @Override
    public Integer save(StockCompanies company)
    {
        update(company, company.getId());
        System.out.println(company +"\n     ^----added to Database" );
        return company.getId();
    }
    //Bread and butter of inserting into a table in the DB
    @Override
    public void update(StockCompanies sc, Integer id)
    {
        Connection connection = null;
        try
        {
            connection = this.connection.getConnection();
            String schema = this.connection.getSchema();
            String preparation = "insert into " + schema +
                    ".stockcompany(id,name) values (" +
                    id+",'"+sc.getName()+"')";
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
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException except)
                {
                    except.printStackTrace();
                }
            }
        }
    }

    //Not updating selected rows, functionality may seem confusing, but there is only updating, update with a response, and delete them
    // This function serves also only for an administrator, but realistically you couldn't just remove a company from the DB,
    // So neither can we. Function only implemented for testing.
    @Override
    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = this.connection.getConnection();
            String schema = this.connection.getSchema();
            String preparation = "delete from " + schema + ".stockcompany where id = " + '\''+id+'\'';
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
}
