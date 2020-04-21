/**
 * the InstrumentSQLRepository extends the Application abstract class
 * and implements the Repository Interface.
 * The main use for this class is to query different functions to the database.
 *
 * functions:
 *
 * findById(int i): This function queries the database and looks for the instrument within the database.
 * It will show nothing, if id is not found. It returns an InstrumentModel object.
 * @params takes in an integer
 *
 * findAll(): This function queries the database and stores all the instruments found within the database.
 * The function returns a List object that stores type InstrumentModel.
 *
 * update(): This function allows you to query and add a new instrument to the database.
 *
 * delete(): This function allows you to query and delete an instrument within the database by accessing
 * it's identification number and instrument name.
 *
 * Setter Functions: setId(), setId(int i), setInstrumentName(), setUsed(), setPrice()
 *
 * These functions differ from the InstrumentModel getter and setter methods by allowing the user to
 * input data that is to be inserted into the database. They are then called within some of
 * the main functions that need them (update(), delete()).
 */
package data;

import app.Application;
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

    // This constructor tests if there is a connection, if not, it provides a connection.
    public InstrumentSQLRepository(ConnectionUtils connectionUtils)
    {
        if(connectionUtils != null)
        {
            this.connectionUtils = connectionUtils;
        }
    }

    // Attempts to query the database to find the instrument with the specified identification number.
    public InstrumentModel findById(int i)
    {
        Connection connection = null;
        this.instrumentModel = new InstrumentModel();
        setId(i);
        try
        {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String instrumentTable = connectionUtils.getInstrumentTable();
            String sql = "select id, name, used, price from " + schemaName + "." + instrumentTable + " where id=" + this.instrumentModel.getId();
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                String instrumentName = rs.getString("name");
                int used = rs.getInt("used");
                float price = rs.getFloat("price");
                this.instrumentModel.setInstrumentName(instrumentName);
                this.instrumentModel.setUsed(used);
                this.instrumentModel.setPrice(price);
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
        return this.instrumentModel;
    }


    // Queries the database, gets all the instruments, and stores it within the List Object.
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


    // Queries the database to allow a new instrument to be added.
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


    // Queries the database to allow the removal of an instrument.
    @Override
    public void delete()
    {
        this.instrumentModel = new InstrumentModel();
        System.out.println("Which instrument would you like to take out?");
        setId();
        setInstrumentName();
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String instrumentTable = connectionUtils.getInstrumentTable();
            String sql = "delete from " + schemaName + "." + instrumentTable + " where " + "name='" + instrumentModel.getInstrumentName() + "' and " + "id=" + instrumentModel.getId();
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


    // Takes in user input and sets the id InstrumentModel to be sent.
    public void setId()
    {
        Scanner scanner = super.getScanner();
        System.out.print("Enter a desired ID#: ");
        try
        {
            this.instrumentModel.setId(scanner.nextInt());
        } catch (InputMismatchException e)
        {
            this.instrumentModel.setId(0);
        }
    }


    // Takes in user input and sets the id InstrumentModel to be sent.
    // This is an overloaded function.
    public void setId(int i)
    {
        System.out.print("Finding instrument with id: " + i + "\n");
        this.instrumentModel.setId(i);
    }


    // Takes in user input and sets the name of the InstrumentModel to be sent.
    public void setInstrumentName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Model Name of the Instrument: ");
        try
        {
            String instrumentName = scanner.nextLine();
            this.instrumentModel.setInstrumentName(instrumentName);
        }
        catch (InputMismatchException e)
        {
            this.instrumentModel.setInstrumentName("Model Null");
        }
    }


    // Takes in user input and sets the used state of the  InstrumentModel to be sent.
    public void setUsed()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Is the instrument used? [0 for new, 1 for used]");
        try
        {
            this.instrumentModel.setUsed(scanner.nextInt());
        }
        catch (InputMismatchException e)
        {
            this.instrumentModel.setUsed(0);
        }
    }


    // Takes in user input and sets the price of the InstrumentModel to be sent.
    public void setPrice()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the instrument's value: ");
        try
        {
            float price = scanner.nextFloat();
            this.instrumentModel.setPrice(price);
        }
        catch (InputMismatchException e)
        {
            this.instrumentModel.setPrice(0);
        }
    }
}
