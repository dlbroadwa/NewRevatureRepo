package data;


import app.Application;
import com.sun.xml.internal.bind.util.Which;
import models.InstrumentModel;
import utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class InstrumentSQLRepository extends Application implements Repository<InstrumentModel, Integer>
{
    private ConnectionUtils connectionUtils;
    private InstrumentModel instrumentModel;
    public InstrumentSQLRepository(ConnectionUtils connectionUtils)
    {
        if(connectionUtils != null)
        {
            this.connectionUtils = connectionUtils;
        }
    }
    @Override
    public InstrumentModel findById(Integer integer)
    {
        return null;
    }

    @Override
    public List<InstrumentModel> findAll()
    {
        Connection connection = null;
        List<InstrumentModel> instruments = new ArrayList<InstrumentModel>();
        try
        {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String instrumentTable = connectionUtils.getInstrumentTable();
            String sql = "select id, name, used, price from " + schemaName + "." + instrumentTable;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("id");
                String instrumentName = rs.getString("name");
                int used = rs.getInt("used");
                float price = rs.getFloat("price");
                InstrumentModel temp = new InstrumentModel();
                temp.setId(id);
                temp.setInstrumentName(instrumentName);
                temp.setUsed(used);
                temp.setPrice(price);
                instruments.add(temp);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return instruments;
    }

    @Override
    public Integer save(InstrumentModel obj)
    {
        return null;
    }

    @Override
    public void update()
    {
        this.instrumentModel = new InstrumentModel();
        setId();
        setInstrumentName();
        setUsed();
        setPrice();
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String instrumentTable = connectionUtils.getInstrumentTable();
            String sql = "insert into " + schemaName + "." + instrumentTable + "(id, name, used, price)"+ " values (" +
                    this.instrumentModel.getId() + " , " + "'" +
                    this.instrumentModel.getInstrumentName() + "', " +
                    this.instrumentModel.getUsed() + ", " +
                    this.instrumentModel.getPrice() + ")";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        }
        catch(SQLException e)
        {
            //e.printStackTrace();
            // Will throw an exception anyway due to being a void method.
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    //e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }

    @Override
    public void delete()
    {
        this.instrumentModel = new InstrumentModel();
        System.out.println("Which instrument would you like to take out?");
        setInstrumentName();
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String instrumentTable = connectionUtils.getInstrumentTable();
            String sql = "delete from " + schemaName + "." + instrumentTable + " where " + "name='" + instrumentModel.getInstrumentName() + "'";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        }
        catch(SQLException e)
        {
            //e.printStackTrace();
            // Will throw an exception anyway due to being a void method.
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    //e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }

    public void setId()
    {
        Scanner scanner = super.getScanner();
        System.out.print("Enter a desired ID#: ");
        this.instrumentModel.setId(scanner.nextInt());
    }

    public void setInstrumentName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Model Name of the Instrument: ");
        String instrumentName = scanner.nextLine();
        this.instrumentModel.setInstrumentName(instrumentName);
    }

    public void setUsed()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Is the instrument used?");
        this.instrumentModel.setUsed(scan.nextInt());
    }

    public void setPrice()
    {
        Scanner scanner = super.getScanner();
        System.out.print("Enter the instrument's value: ");
        float price = scanner.nextFloat();
        this.instrumentModel.setPrice(price);
    }
}
