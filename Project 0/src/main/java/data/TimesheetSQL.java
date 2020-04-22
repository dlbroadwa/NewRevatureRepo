package data;

import dbutility.ConnectionDBUtility;
import models.GrossPay;
import models.Timesheet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Timesheet DAO utilizing scanner object for user input and java table for temporary storage of data. Information from the database is pulled in through the connection utility.
 * This class contains the necessary SQL functions to perform searches, updates, adds, and deletion of data from the Timesheet Table.
 * Find by ID is not currently being used at this time.
 */
public class TimesheetSQL implements Dao<Timesheet, String> {
    //private List<Timesheet> timesheetList;
    private ConnectionDBUtility connectionDBUtility;
    private Scanner scanner = new Scanner(System.in);

    public TimesheetSQL(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public Timesheet findById(GrossPay t) {
            return null;
    }

    @Override
    public List<Timesheet> findAll() {
        Connection connection = null;
        List<Timesheet> timesheet = new ArrayList<>();

        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "select * from " + schemaName + ".timesheet";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int timesheetID = rs.getInt("timesheet");
                String monday =rs.getString("monday");
                String tuesday =rs.getString("tuesday");
                String wednesday =rs.getString("wednesday");
                String thursday =rs.getString("thursday");
                String friday =rs.getString("friday");
                String saturday =rs.getString("saturday");
                String sunday =rs.getString("sunday");
                String userID = rs.getString("userid");

                Timesheet temp = new Timesheet();
                temp.setTimesheetID(timesheetID);
                temp.setMonday(monday);
                temp.setTuesday(tuesday);
                temp.setWednesday(wednesday);
                temp.setThursday(thursday);
                temp.setFriday(friday);
                temp.setSaturday(saturday);
                temp.setSunday(sunday);
                temp.setUserid(userID);
                timesheet.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return timesheet;
    }

    @Override
    public void save(Timesheet obj) {
        Connection connection;
        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "insert into " + schemaName + ".timesheet ( userid, monday, tuesday, wednesday, thursday, friday, saturday, sunday) values (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("User ID: ");
            statement.setString(1, scanner.nextLine());
            System.out.println("Monday: ");
            statement.setDouble(2, Double.parseDouble(scanner.nextLine()));
            System.out.println("Tuesday: ");
            statement.setDouble(3, Double.parseDouble(scanner.nextLine()));
            System.out.println("Wednesday: ");
            statement.setDouble(4, Double.parseDouble(scanner.nextLine()));
            System.out.println("Thursday: ");
            statement.setDouble(5, Double.parseDouble(scanner.nextLine()));
            System.out.println("Friday: ");
            statement.setDouble(6, Double.parseDouble(scanner.nextLine()));
            System.out.println("Saturday: ");
            statement.setDouble(7, Double.parseDouble(scanner.nextLine()));
            System.out.println("Sunday: ");
            statement.setDouble(8, Double.parseDouble(scanner.nextLine()));
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Timesheet was added successfully!\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Timesheet obj) {
        Connection connection;
        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "update " + schemaName + ".timesheet set monday=?, tuesday=?, wednesday=?, thursday=?, friday=?, saturday=?, sunday=? where userid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Please enter the employee's user ID: ");
            statement.setString(8, scanner.nextLine());
            System.out.println("New hours for Monday: ");
            statement.setDouble(1, Double.parseDouble(scanner.nextLine()));
            System.out.println("New hours for Tuesday: ");
            statement.setDouble(2, Double.parseDouble(scanner.nextLine()));
            System.out.println("New hours for Wednesday: ");
            statement.setDouble(3, Double.parseDouble(scanner.nextLine()));
            System.out.println("New hours for Thursday: ");
            statement.setDouble(4, Double.parseDouble(scanner.nextLine()));
            System.out.println("New hours for Friday: ");
            statement.setDouble(5, Double.parseDouble(scanner.nextLine()));
            System.out.println("New hours for Saturday: ");
            statement.setDouble(6, Double.parseDouble(scanner.nextLine()));
            System.out.println("New hours for Sunday: ");
            statement.setDouble(7, Double.parseDouble(scanner.nextLine()));
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User was updated successfully!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Timesheet obj) {
        Connection connection;
        this.scanner = new Scanner(System.in);
        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "delete from " + schemaName + ".timesheet where userid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Please enter the employee's user ID: ");
            statement.setString(1, scanner.nextLine());
            //Statement statement = connection.createStatement();
            //ResultSet rs = statement.executeQuery(sql);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee hours were deleted successfully!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
