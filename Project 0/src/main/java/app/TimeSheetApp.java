package app;

import clients.EmployeeService;
import clients.GrossPayService;
import clients.TimesheetService;
import data.EmployeeSQLRepo;
import data.GrossPaySQLRepo;
import data.Repo;
import data.TimesheetSQLRepo;
import dbutility.ConnectionDBUtility;
import dbutility.PostgresConnectionUtility;
import menu.MainMenu;
import menu.Menu;
import models.Employee;
import models.GrossPay;
import models.Timesheet;

import java.util.List;
import java.util.Scanner;

public class TimeSheetApp extends Application{
    private Menu currentMenu = null;
    private Scanner scanner;
    ConnectionDBUtility connectionDBUtility = new PostgresConnectionUtility(
                "jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com",
                "timesheet_user",
                "ge4s1lly",
                "public");

    Repo<Employee, String> employeeRepo = new EmployeeSQLRepo(connectionDBUtility);
    Repo<Timesheet, Integer> timesheetRepo = new TimesheetSQLRepo(connectionDBUtility);
    Repo<GrossPay, Integer> grossPayRepo = new GrossPaySQLRepo(connectionDBUtility);

    EmployeeService eService = new EmployeeService(employeeRepo);
    TimesheetService tService = new TimesheetService(timesheetRepo);
    GrossPayService gpService = new GrossPayService(grossPayRepo);

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
            currentMenu.makeMenu(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public EmployeeService geteService() {
        return eService;
    }

    public TimesheetService gettService() {
        return tService;
    }

    public GrossPayService getGpService() {
        return gpService;
    }
    //        MainMenu mainMenu = new MainMenu();
//        mainMenu.runMenu();
//        HoursMenu hMenu = new HoursMenu();
//        hMenu.runHoursM();
//        PayMenu pMenu = new PayMenu();
//        pMenu.runPayM();

//    public static void main(String[] args) {
        /*ConnectionDBUtility connectionDBUtility = new PostgresConnectionUtility(
                "jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com",
                "timesheet_user",
                "ge4s1lly",
                "public");

        Repo<Employee, String> employeeRepo = new EmployeeSQLRepo(connectionDBUtility);
        Repo<Timesheet, Integer> timesheetRepo = new TimesheetSQLRepo(connectionDBUtility);
        Repo<GrossPay, Integer> grossPayRepo = new GrossPaySQLRepo(connectionDBUtility);

        EmployeeService eService = new EmployeeService(employeeRepo);
        TimesheetService tService = new TimesheetService(timesheetRepo);
        GrossPayService gpService = new GrossPayService(grossPayRepo);

        List<Employee> allEmployees = eService.getAllEmployees();
        List<Timesheet> allTimesheets = tService.getAllTimesheets();
        List<GrossPay> allGrossPay = gpService.getAllGrossPay();

        MainMenu mainMenu = new MainMenu();
        mainMenu.runMenu();
 *//*       HoursMenu hMenu = new HoursMenu();
        hMenu.runHoursM();
        PayMenu pMenu = new PayMenu();
        pMenu.runPayM();*/
//    }
}
