package com.proj.models;

public class Schedule {

    private String username;
    private String eventName;
    private int eventID;


    public Schedule() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEventName() { return eventName; }

    public void setEventName(String eventName) { this.eventName = eventName; }

    public int getEventID() { return eventID; }

    public void setEventID(int eventID) { this.eventID = eventID; }

}
