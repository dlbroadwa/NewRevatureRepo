package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.Event;

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
}
