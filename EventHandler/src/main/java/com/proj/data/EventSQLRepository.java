package com.proj.data;

import com.proj.models.Event;
import com.proj.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//********************Talking to and updating the events repository in AWS RDB********************//
/**
 * the EventSQL Repository is how java talks to my events repository in AWS/RDB
 * It will call the methods from the event services class and update the AWS/ RDB accordingly
 */
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

        Connection connection = null;
        List<Event> event = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select id, events from " + schemaName + ".events order by id ASC";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String eventName = rs.getString("events");

                Event events = new Event();
                events.setEventName(eventName);
                events.setEventID(id);

                event.add(events);
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
    public void save(Event obj) {
        Connection connection = null;

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "insert into " + schemaName + ".events (events) values " +
                    "( '" + obj.getNewEvent() + "')";
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
    public void update(Event obj) {
        Connection connection = null;

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "update " + schemaName + ".events set events = '"+ obj.getNewEvent() + "' where id = " + obj.getEventID() ;
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
    public void delete(Event obj) {
        Connection connection = null;

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "delete from " + schemaName + ".events (id) value ('" + obj.getEventID() + "')";
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
}
