package app;

import clients.EmployeeService;
import clients.GrossPayService;
import clients.TimesheetService;
import data.EmployeeSQLDao;
import data.GrossPaySQLDao;
import data.Dao;
import data.TimesheetSQLDao;
import dbutility.ConnectionDBUtility;
import dbutility.PostgresConnectionUtility;
import menu.MainMenu;
import menu.Menu;
import models.Employee;
import models.GrossPay;
import models.Timesheet;

import java.util.Scanner;

public class TimeSheetApp extends Application{
    private Menu currentMenu = null;
    private Scanner scanner;
/*    ConnectionDBUtility connectionDBUtility = new PostgresConnectionUtility(
                "jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com",
                "timesheet_user",
                "ge4s1lly",
                "public");*/

/*    Dao<Employee, Integer> employeeDao = new EmployeeSQLDao(connectionDBUtility);
    Dao<Timesheet, Integer> timesheetDao = new TimesheetSQLDao(connectionDBUtility);
    Dao<GrossPay, Integer> grossPayDao = new GrossPaySQLDao(connectionDBUtility);

    EmployeeService eService = new EmployeeService(employeeDao);
    TimesheetService tService = new TimesheetService(timesheetDao);
    GrossPayService gpService = new GrossPayService(grossPayDao);*/

        /*List<Employee> allEmployees = eService.getAllEmployees();
        List<Timesheet> allTimesheets = tService.getAllTimesheets();
        List<GrossPay> allGrossPay = gpService.getAllGrossPay();*/

    public TimeSheetApp(){
        this.scanner = new Scanner(System.in); //reads input
        currentMenu = new MainMenu();
    }

    public TimeSheetApp(String title) {
        this();
        this.title = title;
    }

    @Override
    public void runApp() {
        while(currentMenu != null) {
            currentMenu = currentMenu.makeMenu(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

/*    public EmployeeService getEService() {
        return eService;
    }

    public TimesheetService getTService() {
        return tService;
    }

    public GrossPayService getGpService() {
        return gpService;
    }

    public Dao<Employee, Integer> getEmployeeDao() {
        return employeeDao;
    }

    public Dao<Timesheet, Integer> getTimesheetDao() {
        return timesheetDao;
    }

    public Dao<GrossPay, Integer> getGrossPayDao() {
        return grossPayDao;
    }*/
}
