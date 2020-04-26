package com.game.models;

import java.sql.Timestamp;

public class Message {
    private final String content;
    private final String from;
    private final Timestamp time;

    /**
     * Message object keeps track of who sent the message, the message
     * content, and who it is sent to. The Id attribute is used by the
     * program to identify the corresponding record in the database
     * for quicker removal.
     *
     * Message attributes are final, as they are not intended to change
     * after they are created
     */
    public Message(String content, String from, Timestamp time) {
        this.content = content;
        this.from = from;
        this.time=time;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }

}
