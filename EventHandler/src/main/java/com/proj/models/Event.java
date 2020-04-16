package com.proj.models;

public class Event {
    private int eventID;
    private String eventName;
    //private String time;

    public Event() {}

    public int getEventID() {
        return eventID;
    }

    public void setId(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    //public String getTime() { return time; }

   // public void setTime(String time) { this.time = time; }

}
