package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.Attendees;
import com.proj.models.User;

import java.sql.SQLException;
import java.util.List;

public class AttendeesServices {
    private Repository<Attendees, String> attendeeRepo;

    public AttendeesServices(Repository<Attendees, String> AttendeeRepo) {
        this.attendeeRepo = attendeeRepo;
    }

    public List<Attendees> getAllAttendees() throws SQLException {
        return this.attendeeRepo.findAll();
    }

    public void addAttendee(String name) throws SQLException {
        Attendees newAttendee = new Attendees();
        newAttendee.setAttendeeName(name);
        this.attendeeRepo.save(newAttendee);
    }
}
