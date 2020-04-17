package com.game.models;

public class Message {
    private String message;
    private String to;
    private String from;

    //intended for it to be possible to attach something, but due to different types,
    //of what could be sent, I will put it off for now
    public Message(String message, String to, String from) {
        this.message = message;
        this.to = to;
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
}
