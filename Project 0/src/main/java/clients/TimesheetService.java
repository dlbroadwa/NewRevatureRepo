package clients;

import data.Dao;
import models.Timesheet;

import java.util.List;

public class TimesheetService {
    private Dao<Timesheet, Integer> timeDao;

    public TimesheetService(Dao<Timesheet, Integer> empDao) {
        this.timeDao = empDao;
    }

    public List<Timesheet> getAllTimesheets() {
        return (List<Timesheet>) this.timeDao.findAll();
    }
}
