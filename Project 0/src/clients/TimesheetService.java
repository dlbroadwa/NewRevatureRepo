package clients;

import data.Repo;
import models.Timesheet;

import java.util.List;

public class TimesheetService {
    private Repo<Timesheet, Integer> timeRepo;

    public TimesheetService(Repo<Timesheet, Integer> empRepo) {
        this.timeRepo = empRepo;
    }

    public List<Timesheet> getAllTimesheets() {
        return this.timeRepo.findAll();
    }
}
