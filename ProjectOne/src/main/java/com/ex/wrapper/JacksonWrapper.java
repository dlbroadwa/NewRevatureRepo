package com.ex.wrapper;

import com.ex.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class JacksonWrapper {
    public List<Schedule> schedules;

    public JacksonWrapper() {
        schedules = new ArrayList<>();
    }

    public JacksonWrapper(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "JacksonWrapper{" +
                "schedules=" + schedules +
                '}';
    }
}
