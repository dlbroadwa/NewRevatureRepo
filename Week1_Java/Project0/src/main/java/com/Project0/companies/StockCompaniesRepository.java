package com.Project0.companies;

import com.Project0.Repository;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.postgresql.util.PSQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StockCompaniesRepository implements Repository<StockCompanies, Integer>
{
    private PostgresConnectionUtilities connection;

    public StockCompaniesRepository(PostgresConnectionUtilities connection)
    {
        if (connection!=null) this.connection = connection;
    }

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
                return (new StockCompanies(new String[]{coid, name}));
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

    @Override
    public Integer save(StockCompanies company)
    {
        update(company, company.getId());
        System.out.println(company +"\n     ^----added to Database" );
        return company.getId();
    }

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
            //System.out.println(preparation);
        }
        catch (PSQLException exception)
        {
            exception.printStackTrace();
            //System.out.println("Company already exists in DataBase");
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
            System.out.println("Failed Connection");
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
