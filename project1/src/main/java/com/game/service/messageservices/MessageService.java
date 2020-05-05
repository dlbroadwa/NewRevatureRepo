package com.game.service.messageservices;

import com.game.models.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessageList(String username);
    void clear(String username);
    void send(String username, String touser, String content);
}
