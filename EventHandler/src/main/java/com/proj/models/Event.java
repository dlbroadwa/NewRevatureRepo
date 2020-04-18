package com.proj.models;

public class Event {
    private int eventID;
    private String eventName;
    private String newEvent;
    //private String time;

    public Event() {}

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getNewEvent() { return newEvent; }

    public void setNewEvent(String newEvent) { this.newEvent = newEvent; }



}
