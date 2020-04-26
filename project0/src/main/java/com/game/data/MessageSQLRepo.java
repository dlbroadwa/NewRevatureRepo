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

public class MessageSQLRepo implements Repository<Message, Timestamp> {
    private String name;
    static final Logger logger = Logger.getLogger(MessageSQLRepo.class);
    private final ConnectionUtils connectionUtils;
    static final String PEL = "prepared statement not closed";
    static final String REL = "prepared statement not closed";
    static final String CEL = "Connection did not close";

    public MessageSQLRepo(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * will not use this method; set up messages so that their id is arbitrary
     * @param time would be the timestamp of the message
     */
    @Override
    public Message findById(Timestamp time) {
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
                        rs.getString("fromuser"),rs.getTimestamp("time"),name);
                messageList.add(temp);
            }
        } catch (SQLException e) {
            logger.info("SQL find all failed", e);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { logger.info(REL, e); }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
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
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "insert into " + schemaName + ".messagelist " +
                    "(fromuser,touser,message,time) values (?,?,?,?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1,obj.getFrom());
            ps.setString(2,obj.getTo());
            ps.setString(3,obj.getMessage());
            ps.setTimestamp(4,obj.getTime());
            ps.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            logger.info("SQL save failed", e);
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
            }
        }
    }

    /**
     * Id, aka the primary key, allows for quicker access to the correct
     * record in message list. This method will then delete it.
     * @param time, an arbitrary value assigned once the record is created
     */
    @Override
    public void delete(Timestamp time) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "delete from " + schemaName + ".messagelist " +
                    "where time = ?;";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1,time);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.info("SQL delete failed", e);
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { logger.info(PEL, e); }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { logger.info(CEL, e); }
            }
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
