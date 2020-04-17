package com.game.data;

import com.game.models.Message;
import java.util.List;

public class MessageSQLRepo implements Repository<Message,Integer> {
    private String name;
    List<Message> messageList;

    @Override
    public Message findById(Integer integer) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void save(Message obj) {

    }

    @Override
    public void update(Message newObj) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
