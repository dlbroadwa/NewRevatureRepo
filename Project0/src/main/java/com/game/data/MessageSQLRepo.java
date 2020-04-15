package com.game.data;

import com.game.models.Account;
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
    public Integer save(Message obj) {
        return null;
    }

    @Override
    public void update(Message newObj, Integer integer) {

    }

    @Override
    public void delete(Message obj) {

    }
}
