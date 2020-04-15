package data;


import models.InstrumentModel;
import utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class InstrumentSQLRepository implements Repository<InstrumentModel, Integer>
{
    private ConnectionUtils connectionUtils;
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
    public void update(InstrumentModel newObj, Integer integer)
    {

    }

    @Override
    public void delete(InstrumentModel obj)
    {

    }
}
