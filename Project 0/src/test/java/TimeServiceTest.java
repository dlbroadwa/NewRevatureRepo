import clients.TimesheetService;
import data.Dao;
import models.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class TimeServiceTest {
    TimesheetService service;
    List<Timesheet> timesheets = new ArrayList<>();

    @Mock
    Dao timeSheetSQL;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void useEService() {
        service = new TimesheetService(timeSheetSQL);
        Timesheet tmp = new Timesheet();
        tmp.setTimesheetID(0);
        tmp.setMonday("monday");
        tmp.setTuesday("tuesday");
        tmp.setWednesday("wednesday");
        tmp.setThursday("thursday");
        tmp.setFriday("friday");
        tmp.setSaturday("saturday");
        tmp.setSunday("sunday");
        timesheets.add(tmp);
    }
    @Test
    public void shouldSeeTheList(){
        Mockito.when(timeSheetSQL.findAll()).thenReturn(timesheets);
        List<Timesheet> actual = service.getAllTimesheets();
        Assert.assertArrayEquals("Employees not returned", timesheets.toArray(), actual.toArray());
    }
    @Test
    public void shouldAddToList(){
        Mockito.doNothing().when(timeSheetSQL).save(timesheets);
        Assert.assertFalse("No employees added", timesheets.isEmpty());
    }
}
