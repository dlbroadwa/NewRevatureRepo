package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.Event;
import com.proj.models.User;

import java.sql.SQLException;
import java.util.List;

public class EventServices {
    private Repository<Event, Integer> eventRepo;

    public EventServices(Repository<Event, Integer> eventRepo) {
        this.eventRepo = eventRepo;
    }

    public List<Event> getAllEvents() throws SQLException {
        return this.eventRepo.findAll();
    }

    public void addEvent(String event) throws SQLException {
        Event newEvent = new Event();
        newEvent.setNewEvent(event);
        this.eventRepo.save(newEvent);
    }
}
