package com.game.data;

import com.game.models.Message;
import com.game.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Connection connection;
        List<Message> messageList = new ArrayList<>();
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select message, fromuser, id from " + schemaName + ".messageList " +
                    "where touser = '"+name+"';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Message temp;

            while(rs.next()) {
                temp = new Message(rs.getString("message"),name,
                        rs.getString("fromuser"),rs.getInt("id"));

                messageList.add(temp);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
                    obj.getFrom()+"','"+obj.getTo()+"','"+
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
