package com.game.models;

public class Message {
    private final String content;
    private final String to;
    private final String from;
    private final int id;

    /**
     * Message object keeps track of who sent the message, the message
     * content, and who it is sent to. The Id attribute is used by the
     * program to identify the corresponding record in the database
     * for quicker removal.
     *
     * Message attributes are final, as they are not intended to change
     * after they are created
     */
    public Message(String content, String to, String from, int id) {
        this.content = content;
        this.to = to;
        this.from = from;
        this.id=id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return content;
    }

    public int getId() {
        return id;
    }

}
