package com.proj.models;


/**
 *
 */
public class Attendees {
    private int attendeeID;
    private String attendeeName;
    private String eventName;


    public Attendees() {}

    public int getAttendeeID() { return attendeeID; }

    public void setAttendeeID(int attendeeID) {
        this.attendeeID = attendeeID;
    }

    public String getAttendeeName() {
        return attendeeName;
    }

    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public String getEventName() { return eventName; }

    public void setEventName(String eventName ) { this.eventName = eventName; }

}
