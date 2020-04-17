package com.game.data;

import com.game.models.Message;
import com.game.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageSQLRepo implements Repository<Message, Integer> {
    private String name;
    private int lastId;
    private final ConnectionUtils connectionUtils;

    public MessageSQLRepo(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    //will not use this method; set up messages so that their id is non-unique
    @Override
    public Message findById(Integer s) {
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

            //get new id of row
            sql = "Select id from " + schemaName + ".messageList " +
                    "where message = '"+newObj.getMessage()+"'," +
                    "touser = '"+newObj.getTo()+"'," +
                    "fromuser = '"+newObj.getFrom()+"';";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            lastId = rs.getInt("id");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void setName(String name){
        this.name = name;
    }

    public int getLastId(){
        return lastId;
    }
}
