package data;

import models.Creator;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// T      ID
public class CreatorSQLRepository implements Repository<Creator, Integer>{

    private ConnectionUtils connectionUtils;
    public CreatorSQLRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public Creator findById(Integer integer) {
        return null;
    }

    @Override
    public List<Creator> findAll() {
        Connection connection = null;
        List<Creator> creators = new ArrayList<Creator>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select id, creator_name from " + schemaName + ".creator";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String creatorName = rs.getString("creator_name");

                Creator temp = new Creator();
                temp.setCreatorName(creatorName);
                temp.setId(id);

                creators.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return creators;
    }

    @Override
    public Integer save(Creator obj) {
        return null;
    }

    @Override
    public void update(Creator newObj, Integer integer) {

    }

    @Override
    public void delete(Creator obj) {

    }
}
