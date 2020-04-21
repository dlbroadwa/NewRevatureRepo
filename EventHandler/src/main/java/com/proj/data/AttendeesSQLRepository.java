package com.proj.data;

import com.proj.models.Attendees;
import com.proj.models.Event;
import com.proj.models.User;
import com.proj.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AttendeesSQLRepository implements Repository<Attendees, String> {

    private ConnectionUtils connectionUtils;

    public AttendeesSQLRepository(ConnectionUtils connectionUtils) {
        if (connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }


    @Override
    public Attendees findById(String s) {
        return null;
    }

    @Override
    public List<Attendees> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Attendees obj) throws SQLException {

    }

    @Override
    public void update(Attendees obj) {
        Connection connection = null;

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "insert into " + schemaName + ".attendees (" + obj.getEventName() + ") values ('" + obj.getAttendeeName() +"')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Attendees obj) {

    }
}
