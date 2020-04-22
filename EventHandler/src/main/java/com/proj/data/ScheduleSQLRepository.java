package com.proj.data;

import com.proj.models.Event;
import com.proj.models.Schedule;
import com.proj.utils.ConnectionUtils;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//***********************Talking to and updating the schedule repository in AWS RDB***************************//
/**
 * the ScheduleSQLRepository is how java talks to my schedule repository in AWS/RDB
 * It will call the methods from the schedule services class and update the AWS/ RDB accordingly
 */
public class ScheduleSQLRepository implements Repository<Schedule, String> {

    private ConnectionUtils connectionUtils;
    public ScheduleSQLRepository(ConnectionUtils connectionUtils) {
        if (connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public Schedule findById(String s) {
        return null;
    }

    @Override
    public List<Schedule> findAll() throws SQLException {
        Connection connection = null;
        List<Schedule> schedule = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select id, events from " + schemaName + ".johnny order by id ASC";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String event = rs.getString("events");

                Schedule schedules = new Schedule();
                schedules.setEventName(event);
                schedules.setEventID(id);

                schedule.add(schedules);
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
        return schedule;
    }


    @Override
    public void save(Schedule obj) throws SQLException {

    }

    @Override
    public void update(Schedule obj) {
        Connection connection = null;

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "insert into " + schemaName + "."+ obj.getUsername() +" (events) values ( '" + obj.getEventName() +"' ) ";
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
    public void delete(Schedule obj) {

    }
}
