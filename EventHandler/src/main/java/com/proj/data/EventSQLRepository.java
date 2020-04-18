package com.proj.data;

import com.proj.models.Event;
import com.proj.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventSQLRepository implements Repository<Event, Integer> {

    private ConnectionUtils connectionUtils;

    public EventSQLRepository(ConnectionUtils connectionUtils) {
        if (connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public Event findById(Integer integer) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        //return null;

        Connection connection = null;
        List<Event> event = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select id, events from " + schemaName + ".events";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String eventName = rs.getString("events");
                //String eventTime = rs.getString("time");

                Event temp = new Event();
                temp.setEventName(eventName);
                temp.setId(id);

                event.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return event;
    }

    @Override
    public Integer save(Event obj) {
        return null;
    }

    @Override
    public void update(Event newObj, Integer integer) {

    }

    @Override
    public void delete(Event obj) {

    }
}
