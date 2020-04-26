package com.game.data;

import com.game.models.Message;
import com.game.utils.ConnectionUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository to implement CRUD methods of the DAO interface.
 * Build to manages the messageList table in Postgresql.
 * Not all CRUD methods are necessary so some will be left
 * unimplemented.
 *
 * Had an idea to create another method to return an arraylist
 * with sent messages from a user, but seems redundant and would
 * add unnecessary complexity. Could also be done by having another
 * message repo class.
 */

public class MessageSQLRepo implements Repository<Message, Integer> {
    private String name;
    static final Logger logger = Logger.getLogger(MessageSQLRepo.class);
    private final ConnectionUtils connectionUtils;

    public MessageSQLRepo(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * will not use this method; set up messages so that their id is arbitrary
     * @param s would be the ID
     */
    @Override
    public Message findById(Integer s) {
        return null;
    }

    /**
     * finds all messages to the current account that is signed in
     * @return list of messages that are sent to the user
     */
    @Override
    public List<Message> findAll() {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Message> messageList = new ArrayList<>();
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select message, fromuser, time from " + schemaName + ".messageList " +
                    "where touser = ?;";

            ps = connection.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            Message temp;

            while(rs.next()) {
                temp = new Message(rs.getString("message"),
                        rs.getString("fromuser"),rs.getTimestamp("time"));
                messageList.add(temp);
            }
        } catch (SQLException e) {
            logger.info("SQL find all failed", e);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { logger.info("result set not closed", e); }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info("prepared statement not closed", e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info("Connection did not close", e); }
            }
        }
        return messageList;
    }

    /**
     * Will not use this method; no changes could be made to messages once created.
     * Made it so that those values are final
     * @param obj is the message object
     */
    @Override
    public void update(Message obj) {
        logger.debug("Method not implemented");
    }

    /**
     * Adds new record to the messageList.
     * @param obj - the message object with a from, to, and message strings
     */
    @Override
    public void save(Message obj) {
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "insert into " + schemaName + ".messagelist " +
                    "(fromuser,touser,message) values ('"+
                    obj.getFrom()+"','"+obj.getTime()+"','"+
                    obj.getMessage()+"');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Id, aka the primary key, allows for quicker access to the correct
     * record in message list. This method will then delete it.
     * @param id, an arbitrary value assigned once the record is created
     */
    @Override
    public void delete(Integer id) {
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "delete from " + schemaName + ".messagelist " +
                    "where id = '"+id+"';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows program to recognize what messages to look for in the find all method
     * @param name of the current user
     */
    public void setName(String name){
        this.name = name;
    }
}
