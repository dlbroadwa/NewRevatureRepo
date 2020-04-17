package com.game.data;

import com.game.models.Message;
import com.game.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageSQLRepo implements Repository<Message,String> {
    public String name;
    List<Message> messageList;
    private ConnectionUtils connectionUtils;

    public MessageSQLRepo(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    //will not use this method; set up messages so that their id is non-unique
    @Override
    public Message findById(String s) {
        return null;
    }

    //finds all messages to the current account that is signed in
    @Override
    public List<Message> findAll() {
        Connection connection;
        List<Message> messageList = new ArrayList<>();
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select message, from fromuser " + schemaName + ".messageList " +
                    "where touser = '"+name+"';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Message temp;

            while(rs.next()) {
                temp = new Message(rs.getString("message"),name,
                        rs.getString("from"));

                messageList.add(temp);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    //will not use this method; no changes could be made to messages once created
    @Override
    public void update(Message obj) {
    }

    //add new message to database
    @Override
    public void save(Message newObj) {
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "insert into " + schemaName + ".messagelist " +
                    "(fromuser,touser,message) values ('"+
                    newObj.getFrom()+"','"+newObj.getTo()+"','"+
                    newObj.getMessage()+"');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String s) {
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "delete from " + schemaName + ".messagelist " +
                    "where touser = '"+s+"';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
