package clients;

import data.Dao;
import models.Timesheet;

import java.util.List;

/**
 * Timesheet service that utilizes the timesheet dao to help perform search, update, add, and delete functions in the Timesheet SQL DAO
 */
public class TimesheetService {
    private Dao<Timesheet, String> timeDao;

    public TimesheetService(Dao<Timesheet, String> empDao) {
        this.timeDao = empDao;
    }

    public List<Timesheet> getAllTimesheets() {
        return this.timeDao.findAll();
    }
}
