package menu;

import app.Application;
import clients.EmployeeService;
import clients.GrossPayService;
import clients.TimesheetService;
import data.Dao;
import data.EmployeeSQL;
import data.GrossPaySQL;
import data.TimesheetSQL;
import dbutility.ConnectionDBUtility;
import dbutility.PostgresConnectionUtility;
import models.Employee;
import models.GrossPay;
import models.Timesheet;
import java.util.List;
import java.util.Scanner;

/**
 * Primary menu for accessing database options for both the Employee and Timesheet tables. Choosing a specific option
 * will provide prompts for user action for searching, updating, adding, or deleting data.
 */

public class MainMenu implements Menu{
    //contains com.main menu
    boolean exit;
//    private HoursMenu hMenu;
//    private PayMenu pMenu;
    private Object MainMenu;
    private Object Menu;
    private Employee emp = new Employee();
    private Timesheet time = new Timesheet();
    private GrossPay gP = new GrossPay();
    private Scanner scanner = new Scanner(System.in);

    ConnectionDBUtility connectionDBUtility = new PostgresConnectionUtility(
            "jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com:5432/Project 0",
            "timesheet_user",
            "ge4s1lly",
            "public");

    @Override
    public Menu makeMenu(Application app) {
        //Scanner scanner = ((TimeSheetApp)app).getScanner();
        printHeader();
        while(!exit){
            mainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            menuAction(choice);
        }
        //return (Menu) MainMenu;
        return null;
    }

    private void printHeader(){
        System.out.println("Welocome to Timesheets!");
    }

    static menu.Menu mainMenu(){
        System.out.println("Please make a selection: \n");
        System.out.println("1:\t View All Employees");
        System.out.println("2:\t Update Employee");
        System.out.println("3:\t Add Employee");
        System.out.println("4:\t Remove Employee");
        System.out.println("5:\t All Weekly Hours");
        System.out.println("6:\t Add Hours");
        System.out.println("7:\t Update Hours");
//        System.out.println("8:\t View Employee Hours/Pay");
        System.out.println("0:\t Exit");
        //return null;
        //return null;
        return null;
    }

    Dao<Employee, String> employeeDao = new EmployeeSQL(connectionDBUtility);
    Dao<Timesheet, String> timesheetDao = new TimesheetSQL(connectionDBUtility);
    Dao<GrossPay, String> grossPayDao = new GrossPaySQL(connectionDBUtility);

    EmployeeService eService = new EmployeeService(employeeDao);
    TimesheetService tService = new TimesheetService(timesheetDao);
    GrossPayService gpService = new GrossPayService(grossPayDao);

    //Menu with options to access submenus
    public Menu menuAction(int choice){

        switch (choice){
            case 0:
                exit = true;
                System.out.println("Thanks for using Timesheets!");
                System.exit(0);
                break;
            case 1:
                System.out.println("Here are the employees: \n");
                List<Employee> allEmployees = eService.getAllEmployees();
                for(Employee e : allEmployees) {
                    System.out.println("Employee: " + '\n'
                            + "First Name: [" + e.getFirstName() + "] "
                            + "Last Name: [" + e.getLastName() + "] "
                            + "Hourly Pay: [" + e.getHourly_Salary() + "] "
                            + "User ID: [" + e.getUserid() + "] ");
                    System.out.println(/*allEmployees.toString()*/);
                }
                break;
            case 2:
                System.out.println("Lets update an Employees info. \n");
                employeeDao.update(emp);
                break;
            case 3:
                System.out.println("Lets add a new Employee. \n");
                employeeDao.save(emp);
//                System.out.println("New employee added.");
                break;
            case 4:
                System.out.println("Lets take someone out! \n");
                employeeDao.delete(emp);
                break;
            case 5:
                System.out.println("Here are all employee hours worked: \n");
                List<Timesheet> allTimesheets = tService.getAllTimesheets();
                for(Timesheet t : allTimesheets) {
                    System.out.println("Timesheet ID: "
                            + t.getTimesheetID() + '\n'
                            + "Monday: [" + t.getMonday() + "] "
                            + "Tuesday: [" + t.getTuesday() + "] "
                            + "Wednesday: [" + t.getWednesday() + "] "
                            + "Thursday: [" + t.getThursday() + "] "
                            + "Friday: [" + t.getFriday() + "] "
                            + "Saturday: [" + t.getSaturday() + "] "
                            + "Sunday: [" + t.getSaturday() + "] "
                            + "UserID: [" + t.getUserid() + "] ");
                    System.out.println(/*allTimesheets.toString()*/);
                }
                break;
            case 6:
                System.out.println("Who's hours are we adding? \n");
                timesheetDao.save(time);
                break;
            case 7:
                System.out.println("Who's hours are we updating? \n");
                timesheetDao.update(time);
                break;
//            case 8:
//                System.out.println("Let's see someone's total hours and gross pay. \n");
//                grossPayDao.findById(gP);
//                List<GrossPay> grossPays = gpService.getAllGrossPay();
//                for(GrossPay g : grossPays) {
//                    System.out.println("Timesheet ID: "
//                            + g.getGrossPayID() + '\n'
//                            + "User ID: [" + g.getUserid() + "] "
//                            + "Total Hours: [" + g.getTotalHours() + "] "
//                            + "Pay: [" + g.getPay() + "] ");
//                    System.out.println(/*allTimesheets.toString()*/);
//                }
//                break;
            default:
                System.out.println("That isn't a valid menu option.\n");
        }
        return (Menu) MainMenu;
    }
}
