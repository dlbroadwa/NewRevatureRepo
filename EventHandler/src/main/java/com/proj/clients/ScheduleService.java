package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.Schedule;

import java.sql.SQLException;
import java.util.List;

//*******************Methods for updating the user schedules tables in AWS RDB*********************//
/**
 * the event services holds methods to be called to change the data
 * inside the user schedule tables of my AWS RDB
 */
public class ScheduleService {
    private Repository<Schedule, String> scheduleRepo;

    public ScheduleService(Repository<Schedule, String> scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public List<Schedule> getAllEvents() throws SQLException {
        return this.scheduleRepo.findAll();
    }

    public void updateSchedule(String event, String user) {
        Schedule newEvent = new Schedule();
        newEvent.setUsername(user);
        newEvent.setEventName(event);
        this.scheduleRepo.update(newEvent);
    }

    public void setEvent(String event) {
        Schedule sEvent = new Schedule();
        sEvent.setUsername(event);
        this.scheduleRepo.update(sEvent);
    }

    public void setUser(String user) {
        Schedule newUser = new Schedule();
        newUser.setUsername(user);
        this.scheduleRepo.update(newUser);
    }
}
