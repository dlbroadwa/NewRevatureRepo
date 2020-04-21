package com.proj.models;

//***************************************Events Model***************************************//
/**
 * the Event Class is a model for calling all the variables needed to change and update the AWSRDB
 */
public class Event {
    private int eventID;
    private String eventName;
    private String newEvent;
    private String deleteEventName;
    private String username;

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
/*
    public String getDeleteEventName() { return deleteEventName; }

    public void setDeleteEventName(String newEvent) { this.deleteEventName = deleteEventName; }

    public String getUserame() { return username; }

    public void setUsername(String username) { this.username = username; }
 */

    @Override
    public String toString() {
        return  eventName;
    }
}
