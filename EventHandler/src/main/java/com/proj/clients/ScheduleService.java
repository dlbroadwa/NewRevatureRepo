package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.Event;
import com.proj.models.Schedule;

import java.sql.SQLException;
import java.util.List;


public class ScheduleService {
    private Repository<Schedule, String> scheduleRepo;

    public ScheduleService(Repository<Schedule, String> scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public List<Schedule> getAllEvents() throws SQLException {
        return this.scheduleRepo.findAll();
    }

    public void updateSchedule(String event, String user) throws SQLException {
        Schedule newEvent = new Schedule();
        newEvent.setUsername(user);
        newEvent.setEventName(event);
        this.scheduleRepo.update(newEvent);
    }

    public void setEvent(String event) throws SQLException {
        Schedule sevent = new Schedule();
        sevent.setUsername(event);
        this.scheduleRepo.update(sevent);
    }

    public void setUser(String user) throws SQLException {
        Schedule newUser = new Schedule();
        newUser.setUsername(user);
        this.scheduleRepo.update(newUser);
    }
}
