package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.Event;

import java.sql.SQLException;
import java.util.List;

//**********************Methods for updating the events table in AWS RDB*********************//
/**
 * the event services holds methods to be called to change the data
 * inside the events table of my AWS RDB
 */
public class EventServices {
    private Repository<Event, Integer> eventRepo;

    public EventServices(Repository<Event, Integer> eventRepo) {
        this.eventRepo = eventRepo;
    }

    public List<Event> getAllEvents() throws SQLException {
        return this.eventRepo.findAll();
    }

    public void updateEvent(String event, int id) {
        Event changeEvent = new Event();
        changeEvent.setNewEvent(event);
        changeEvent.setEventID(id);
        this.eventRepo.update(changeEvent);
    }
}
